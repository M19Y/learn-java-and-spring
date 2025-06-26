package com.m19y.learn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m19y.learn.entity.User;
import com.m19y.learn.model.auth.LoginUserRequest;
import com.m19y.learn.model.auth.TokenResponse;
import com.m19y.learn.model.web.WebErrorResponse;
import com.m19y.learn.model.web.WebResponse;
import com.m19y.learn.repository.UserRepository;
import com.m19y.learn.security.BCrypt;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

  private final MockMvc mockMvc;
  private final UserRepository userRepository;
  private final ObjectMapper mapper;

  @Autowired
  public AuthControllerTest(MockMvc mockMvc, UserRepository userRepository, ObjectMapper mapper) {
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
  class LoginTest{
    @Test
    @SneakyThrows
    void login_withUnregisteredUser_shouldThrowUnauthorized() {

      LoginUserRequest request = LoginUserRequest.builder()
              .username("unregistered-user")
              .password("simple")
              .build();

      mockMvc.perform(
              post("/api/auth/login")
                      .contentType(MediaType.APPLICATION_JSON)
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(
              status().isUnauthorized()
      ).andDo(result -> {
        WebErrorResponse response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(response);
        assertEquals("Username or password wrong!", response.errors());
      });
    }

    @Test
    @SneakyThrows
    void login_withWrongPassword_shouldThrowUnauthorized() {
      User user = new User();
      user.setName("bro G");
      user.setUsername("bro");
      user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));

      userRepository.save(user);

      LoginUserRequest request = LoginUserRequest.builder()
              .username("bro")
              .password("wrong")
              .build();

      mockMvc.perform(
              post("/api/auth/login")
                      .contentType(MediaType.APPLICATION_JSON)
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(
              status().isUnauthorized()
      ).andDo(result -> {
        WebErrorResponse response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(response);
        assertEquals("Username or password wrong!", response.errors());
      });
    }

    @Test
    @SneakyThrows
    void login_withValidCredentials_shouldReturnToken() {
      User user = new User();
      user.setName("bro G");
      user.setUsername("bro");
      user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));

      userRepository.save(user);

      LoginUserRequest request = LoginUserRequest.builder()
              .username("bro")
              .password("secret")
              .build();

      mockMvc.perform(
              post("/api/auth/login")
                      .contentType(MediaType.APPLICATION_JSON)
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
      ).andExpectAll(
              status().isOk()
      ).andDo(result -> {
        WebResponse<TokenResponse> response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(response);

        TokenResponse token = response.getData();
        assertNotNull(token);
        assertNotNull(token.getToken());
        assertNotNull(token.getExpiredAt());

        assertFalse(token.getToken().isEmpty());
        assertTrue(token.getExpiredAt() > System.currentTimeMillis());

        // query ke db
        User userFromDb = userRepository.findById("bro").orElse(null);

        assertNotNull(userFromDb);
        assertEquals(token.getToken(), userFromDb.getToken());
        assertEquals(token.getExpiredAt(), userFromDb.getTokenExpiredAt());
      });
    }
  }

  @Nested
  class LogoutTest{

    @Test
    @SneakyThrows
    void logout_withTokenNotProvided_shouldThrowUnauthorized() {

      mockMvc.perform(
              delete("/api/auth/logout")
      ).andExpectAll(
              status().isUnauthorized()
      ).andDo(result -> {
        WebErrorResponse response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(response);
        assertEquals("Unauthorized", response.errors());
      });
    }

    @Test
    @SneakyThrows
    void logout_withValidToken_shouldReturnStatusCodeNoContent() {

      final String token = "simple-token";
      final String username = "bro";

      User user = new User();
      user.setName("bro G");
      user.setUsername(username);
      user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
      user.setToken(token);
      user.setTokenExpiredAt(System.currentTimeMillis() + 100_000_000);

      userRepository.save(user);

      mockMvc.perform(
              delete("/api/auth/logout")
                      .header("X-API-TOKEN", token)
      ).andExpectAll(
              status().isNoContent()
      );

      User userFromDb = userRepository.findById(username).orElse(null);
      assertNotNull(userFromDb);

      assertNull(userFromDb.getToken());
      assertNull(userFromDb.getTokenExpiredAt());
    }
  }


}