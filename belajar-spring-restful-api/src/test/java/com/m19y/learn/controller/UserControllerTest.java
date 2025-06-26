package com.m19y.learn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m19y.learn.entity.User;
import com.m19y.learn.model.user.RegisterUserRequest;
import com.m19y.learn.model.user.UpdateUserRequest;
import com.m19y.learn.model.user.UserResponse;
import com.m19y.learn.model.web.WebErrorResponse;
import com.m19y.learn.model.web.WebResponse;
import com.m19y.learn.repository.UserRepository;
import com.m19y.learn.security.BCrypt;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.Period;
import java.util.Map;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class UserControllerTest {

  private final MockMvc mockMvc;
  private final UserRepository userRepository;
  private final ObjectMapper mapper;

  @Autowired
  public UserControllerTest(MockMvc mockMvc, UserRepository userRepository, ObjectMapper mapper) {

    this.mockMvc = mockMvc;
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    userRepository.deleteAll();
  }

  @Nested
  class RegisterTests{

    @Test
    void testRegisterNewUserSuccess() throws Exception {
      RegisterUserRequest request = RegisterUserRequest.builder()
              .name("new-user-name-test")
              .password("secret-pass")
              .username("new-user-1")
              .build();

      mockMvc.perform(
              post("/api/users")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(status().isCreated()
      ).andDo( result -> {
        WebResponse<String> response = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {
        });

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("OK", response.getData());

      });
    }

    @Test
    void testRegisterNewUserFailedThrowConstraints() throws Exception {
      RegisterUserRequest request = RegisterUserRequest.builder()
              .name("")
              .password("")
              .username("")
              .build();

      mockMvc.perform(
              post("/api/users")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(status().isBadRequest()
      ).andDo( result -> {
        Map<String, String> response = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertNotNull(response);
        assertEquals(3, response.size());
        response.forEach((k, v) -> System.out.println(k + " : " + v));
      });
    }

    @Test
    void testRegisterErrorUsernameAlreadyRegistered() throws Exception {
      User user = new User();
      user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
      user.setName("Bro");
      user.setUsername("test-uname");
      userRepository.save(user);

      RegisterUserRequest request = RegisterUserRequest.builder()
              .name("test")
              .password("test")
              .username("test-uname") // username already exsist
              .build();

      mockMvc.perform(
              post("/api/users")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(status().isBadRequest()
      ).andDo( result -> {
        WebErrorResponse response = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        assertNotNull(response);
        assertEquals("Username already registered!", response.errors());
      });
    }
  }

  @Nested
  class GetUser{

    @Test
    @SneakyThrows
    void get_withInvalidToken_shouldThrowUnauthorized() {

      mockMvc.perform(
              get("/api/users/current")
                      .header("X-API-TOKEN", "try-invalid-token")
                      .accept(MediaType.APPLICATION_JSON)
      ).andExpectAll(
              status().isUnauthorized()
      ).andDo(result -> {
        WebErrorResponse response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(response);
        assertEquals("Unauthorized", response.errors());

      });
    }

    @Test
    @SneakyThrows
    void get_withTokenNotProvided_shouldThrowUnauthorized() {

      mockMvc.perform(
              get("/api/users/current")
                      .accept(MediaType.APPLICATION_JSON)
      ).andExpectAll(
              status().isUnauthorized()
      ).andDo(result -> {
        WebErrorResponse response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(response);
        assertEquals("Unauthorized", response.errors());

      });
    }

    @Test
    @SneakyThrows
    void get_withValidToken_shouldReturnUserResponse() {

      final String token_uni = "x_token_uni";
      final String id = "test-uname";

      User user = new User();
      user.setToken(token_uni);
      user.setName("test-name");
      user.setUsername(id);
      user.setPassword(BCrypt.hashpw("test-secret", BCrypt.gensalt()));
      user.setTokenExpiredAt(Instant.now().plus(Period.ofDays(30)).toEpochMilli());

      userRepository.save(user);

      mockMvc.perform(
              get("/api/users/current")
                      .header("X-API-TOKEN", token_uni)
                      .accept(MediaType.APPLICATION_JSON)
      ).andExpectAll(
              status().isOk()
      ).andDo(result -> {
        WebResponse<UserResponse> webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(webResponse);

        UserResponse userResponse = webResponse.getData();

        assertNotNull(userResponse);

        User userFromDb = userRepository.findById(id).orElse(null);
        assertNotNull(userFromDb);

        assertEquals(userFromDb.getName(), userResponse.name());
        assertEquals(userFromDb.getUsername(), userResponse.username());

      });
    }

    @Test
    @SneakyThrows
    void get_withExpiredToken_shouldThrowUnauthorized() {

      final String token_uni = "x_token_uni";
      final String id = "test-uname";

      User user = new User();
      user.setToken(token_uni);
      user.setName("test-name");
      user.setUsername(id);
      user.setPassword(BCrypt.hashpw("test-secret", BCrypt.gensalt()));
      user.setTokenExpiredAt(System.currentTimeMillis() - 1_000_000);

      userRepository.save(user);

      mockMvc.perform(
              get("/api/users/current")
                      .header("X-API-TOKEN", token_uni)
                      .accept(MediaType.APPLICATION_JSON)
      ).andExpectAll(
              status().isUnauthorized()
      ).andDo(result -> {
        WebErrorResponse webResponse = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {});

        assertNotNull(webResponse);
        assertNotNull(webResponse.errors());
        assertEquals("Unauthorized", webResponse.errors());


      });
    }
  }
  
  @Nested
  class UpdateTest{

    @Test
    @SneakyThrows
    void update_withTokenNotProvided_shouldThrowUnauthorized() {

      UpdateUserRequest request = new UpdateUserRequest("bro", "secret");

      mockMvc.perform(
              patch("/api/users/current")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(
              status().isUnauthorized()
      ).andDo(result -> {
        WebErrorResponse response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(response);
        assertEquals("Unauthorized", response.errors());

      });
    }

    @Test
    @SneakyThrows
    void update_withInvalidToken_shouldThrowUnauthorized() {

      UpdateUserRequest request = new UpdateUserRequest("bro", "secret");

      mockMvc.perform(
              patch("/api/users/current")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", "invalid-token")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(
              status().isUnauthorized()
      ).andDo(result -> {
        WebErrorResponse response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(response);
        assertEquals("Unauthorized", response.errors());
      });
    }

    @Test
    @SneakyThrows
    void update_withExpiredToken_shouldThrowUnauthorized() {

      final String token_uni = "x_token_uni";
      final String id = "test-uname";

      User user = new User();
      user.setToken(token_uni);
      user.setName("test-name");
      user.setUsername(id);
      user.setPassword(BCrypt.hashpw("test-secret", BCrypt.gensalt()));
      user.setTokenExpiredAt(System.currentTimeMillis() - 1_000_000);

      userRepository.save(user);

      UpdateUserRequest request = new UpdateUserRequest("bro", "secret");

      mockMvc.perform(
              patch("/api/users/current")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", token_uni)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(
              status().isUnauthorized()
      ).andDo(result -> {
        WebErrorResponse response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(response);
        assertEquals("Unauthorized", response.errors());
      });
    }

    @Test
    @SneakyThrows
    void update_withValidToken_shouldReturnUserResponse() {

      final String token_uni = "x_token_uni";
      final String id = "test-uname";
      final String password = "test-secret";

      User user = new User();
      user.setToken(token_uni);
      user.setName("test-name");
      user.setUsername(id);
      user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
      user.setTokenExpiredAt(System.currentTimeMillis() + 1_000_000);

      userRepository.save(user);

      UpdateUserRequest request = new UpdateUserRequest("bro", "secret");

      mockMvc.perform(
              patch("/api/users/current")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", token_uni)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(
              status().isOk()
      ).andDo(result -> {
        WebResponse<UserResponse> response = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {});

        assertNotNull(response);

        UserResponse userResponse = response.getData();

        assertNotNull(userResponse);

        User userFromDb = userRepository.findById(id).orElse(null);
        assertNotNull(userFromDb);

        assertEquals(userFromDb.getName(), userResponse.name());
        assertTrue(BCrypt.checkpw(password, userFromDb.getPassword()));

      });
    }
  }

}