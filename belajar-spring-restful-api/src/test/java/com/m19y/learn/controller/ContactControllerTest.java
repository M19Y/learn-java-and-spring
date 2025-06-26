package com.m19y.learn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m19y.learn.entity.Contact;
import com.m19y.learn.entity.User;
import com.m19y.learn.model.contact.ContactResponse;
import com.m19y.learn.model.contact.CreateContactRequest;
import com.m19y.learn.model.contact.UpdateContactRequest;
import com.m19y.learn.model.res.web.PagedResponse;
import com.m19y.learn.model.user.UserResponse;
import com.m19y.learn.model.web.WebErrorResponse;
import com.m19y.learn.model.web.WebResponse;
import com.m19y.learn.repository.ContactRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class ContactControllerTest {

  private final MockMvc mockMvc;
  private final ObjectMapper mapper;
  private final ContactRepository contactRepository;
  private final UserRepository userRepository;
  private final String TOKEN = "my-token";
  private final String USERNAME = "bro";

  @Autowired
  public ContactControllerTest(MockMvc mockMvc, ObjectMapper mapper, ContactRepository contactRepository, UserRepository userRepository) {
    this.mockMvc = mockMvc;
    this.mapper = mapper;
    this.contactRepository = contactRepository;
    this.userRepository = userRepository;
  }

  @BeforeEach
  void setUp() {
    contactRepository.deleteAll();
    userRepository.deleteAll();

    User user = new User();
    user.setTokenExpiredAt(System.currentTimeMillis() + 10_000_000);
    user.setToken(TOKEN);
    user.setUsername(USERNAME);
    user.setName("Bro G");
    user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));

    userRepository.save(user);
  }

  @AfterEach
  void tearDown() {
    contactRepository.deleteAll();
    userRepository.deleteAll();
  }

  
  @Nested
  class CreateContactTest{

    @Test
    @SneakyThrows
    void create_withInvalidFieldsSent_shouldThrowConstraints() {

    CreateContactRequest request = CreateContactRequest.builder()
            .firstName(" ")
            .lastName(" ")
            .email("wrong-email")
            .phone("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111")
            .build();

      mockMvc.perform(
              post("/api/contacts")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isBadRequest()
      ).andDo(result -> {
        Map<String, String> errorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(errorResponse);
        assertEquals(4, errorResponse.size());
      });
    }

    @Test
    @SneakyThrows
    void create_withValidRequest_shouldReturnStatusCode201() {

      CreateContactRequest request = CreateContactRequest.builder()
              .firstName("lil")
              .lastName("bro")
              .email("lilbro@gmail.com")
              .phone("1234567890")
              .build();

      mockMvc.perform(
              post("/api/contacts")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isCreated()
      ).andDo(result -> {
         WebResponse<ContactResponse> webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(webResponse);

        ContactResponse contactResponse = webResponse.getData();
        User userFromDb = userRepository.findById(USERNAME).orElse(null);

        assertNotNull(userFromDb);

        Contact contact = contactRepository.findById(contactResponse.id()).orElse(null);

        assertNotNull(contact);

        assertEquals(request.getFirstName(), contact.getFirstName());
        assertEquals(request.getLastName(), contact.getLastName());
        assertEquals(request.getEmail(), contact.getEmail());
        assertEquals(request.getPhone(), contact.getPhone());

        assertNotNull(result.getResponse().getHeader("Location")); // harus memiliki response header location

      });
    }
  }

  @Nested
  class GetContactTest{

    @Test
    @SneakyThrows
    void get_withIdContactNotExists_shouldThrowHttp401Indonesia() {

      mockMvc.perform(
              get("/api/contacts/not-found")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
                      .header("Accept-Language", "id-ID")
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse errorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(errorResponse);
        assertEquals("Kontak tidak ditemukan!", errorResponse.errors());
      });
    }

    @Test
    @SneakyThrows
    void get_withIdContactNotExists_shouldThrowHttp401Eng() {

      mockMvc.perform(
              get("/api/contacts/not-found")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse errorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(errorResponse);
        assertEquals("Contact not found!", errorResponse.errors());
      });
    }

    @Test
    @SneakyThrows
    void get_withValidId_shouldReturnContactResponse() {
      User user = userRepository.findById(USERNAME).orElse(null);

      assertNotNull(user);

      Contact contact = new Contact();
      contact.setId(UUID.randomUUID().toString());
      contact.setUser(user);
      contact.setEmail("email@example.com");
      contact.setFirstName("first");
      contact.setLastName("last");

      contactRepository.save(contact);

      mockMvc.perform(
              get("/api/contacts/" + contact.getId())
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        WebResponse<ContactResponse> webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(webResponse);

        ContactResponse contactResponse = webResponse.getData();

        assertNotNull(contactResponse);

        assertEquals(contact.getId(), contactResponse.id());
        assertEquals(contact.getFirstName(), contactResponse.firstName());
        assertEquals(contact.getLastName(), contactResponse.lastName());
        assertEquals(contact.getEmail(), contactResponse.email());
        assertNull(contactResponse.phone()); // we are able to set is as null (not throw any error)
      });
    }
  }

  @Nested
  class UpdateContactTest{

    @Test
    @SneakyThrows
    void update_withIdNotExists_shouldThrowHttp401() {

        UpdateContactRequest request = UpdateContactRequest.builder()
              .firstName("first")
              .lastName("last")
              .email("good@gmail.com")
              .phone("1234567890")
              .build();

      mockMvc.perform(
              put("/api/contacts/not-found")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .header("Accept-Language", "id-ID")
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse errorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(errorResponse);
        assertEquals("Kontak tidak ditemukan!", errorResponse.errors());
      });
    }

    @Test
    @SneakyThrows
    void update_withInvalidFieldsSent_shouldThrowConstraints() {
      User user = userRepository.findById(USERNAME).orElse(null);
      assertNotNull(user);
      Contact contact = new Contact();
      contact.setId(UUID.randomUUID().toString());
      contact.setUser(user);
      contact.setEmail("email@example.com");
      contact.setFirstName("first");
      contact.setLastName("last");

      contactRepository.save(contact);

      UpdateContactRequest request = UpdateContactRequest.builder()
              .firstName(" ")
              .lastName(" ")
              .email("wrong-email")
              .phone("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111")
              .build();

      mockMvc.perform(
              put("/api/contacts/" + contact.getId())
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isBadRequest()
      ).andDo(result -> {
        Map<String, String> errorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(errorResponse);
        assertEquals(4, errorResponse.size());
      });
    }

    @Test
    @SneakyThrows
    void update_withValidRequest_shouldReturnUserResponse() {
      User user = userRepository.findById(USERNAME).orElse(null);

      assertNotNull(user);

      Contact contact = new Contact();
      contact.setId(UUID.randomUUID().toString());
      contact.setUser(user);
      contact.setEmail("email@example.com");
      contact.setFirstName("first");
      contact.setLastName("last");

      contactRepository.save(contact);

      UpdateContactRequest request = UpdateContactRequest.builder()
              .firstName("first")
              .lastName("last")
              .email("good@gmail.com")
              .phone("0987654321")
              .build();

      mockMvc.perform(
              put("/api/contacts/" + contact.getId())
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        WebResponse<ContactResponse> webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        ContactResponse contactResponse = webResponse.getData();
        assertNotNull(contactResponse);

        assertEquals(request.getFirstName(), contactResponse.firstName());
        assertEquals(request.getLastName(), contactResponse.lastName());
        assertEquals(request.getEmail(), contactResponse.email());
        assertEquals(request.getPhone(), contactResponse.phone());
      });
    }
  }

  @Nested
  class DeleteContactTest{
    @Test
    @SneakyThrows
    void delete_withIdNotExists_shouldThrowHttp401() {

      mockMvc.perform(
              delete("/api/contacts/not-found")
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse errorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(errorResponse);
        assertEquals("Contact not found!", errorResponse.errors());
      });
    }

    @Test
    @SneakyThrows
    void delete_withIdExists_shouldReturnHttp204() {
      User user = userRepository.findById(USERNAME).orElse(null);

      assertNotNull(user);

      Contact contact = new Contact();
      contact.setId(UUID.randomUUID().toString());
      contact.setUser(user);
      contact.setEmail("email@example.com");
      contact.setFirstName("first");
      contact.setLastName("last");

      contactRepository.save(contact);

      mockMvc.perform(
              delete("/api/contacts/" + contact.getId())
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNoContent());

      Contact contactFromDb = contactRepository.findById(contact.getId()).orElse(null);
      assertNull(contactFromDb);
    }
  }

  @Nested
  class SearchContactTest{

    @Test
    @SneakyThrows
    void search_withNotExistingContacts_shouldReturnEmptyList() {

      mockMvc.perform(
              get("/api/contacts")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        PagedResponse<List<ContactResponse>> webResponse = mapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(webResponse);
        List<ContactResponse> contactResponses = webResponse.getData();
        assertTrue(contactResponses.isEmpty());

        assertEquals(0, webResponse.getCurrentPage());
        assertEquals(10, webResponse.getSize());
        assertEquals(0, webResponse.getTotalPage());

      });
    }

    @Test
    @SneakyThrows
    void search_withQueryNameEmailAndPhone_shouldReturnListOfUserResponse() {

      User user = userRepository.findById(USERNAME).orElse(null);

      assertNotNull(user);

      for (int i = 1; i <= 100; i++) {
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setUser(user);
        contact.setEmail("email" + i +"@example.com");
        contact.setFirstName("first" + i);
        contact.setLastName("last");
        contact.setPhone(String.valueOf(i));
        contactRepository.save(contact);
      }


      // query param (name)
      mockMvc.perform(
              get("/api/contacts")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
                      .queryParam("name", "first")
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        PagedResponse<List<ContactResponse>> webResponse = mapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(webResponse);
        List<ContactResponse> contactResponses = webResponse.getData();
        assertFalse(contactResponses.isEmpty());

        assertEquals(10, contactResponses.size());
        assertEquals(0, webResponse.getCurrentPage());
        assertEquals(10, webResponse.getSize());
        assertEquals(10, webResponse.getTotalPage());

      });

      // query param (email)
      mockMvc.perform(
              get("/api/contacts")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
                      .queryParam("email", "example")
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        PagedResponse<List<ContactResponse>> webResponse = mapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(webResponse);
        List<ContactResponse> contactResponses = webResponse.getData();
        assertFalse(contactResponses.isEmpty());

        assertEquals(10, contactResponses.size());
        assertEquals(0, webResponse.getCurrentPage());
        assertEquals(10, webResponse.getSize());
        assertEquals(10, webResponse.getTotalPage());

      });

      // query param (phone)
      mockMvc.perform(
              get("/api/contacts")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
                      .queryParam("phone", "9")
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        PagedResponse<List<ContactResponse>> webResponse = mapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(webResponse);
        List<ContactResponse> contactResponses = webResponse.getData();
        assertFalse(contactResponses.isEmpty());

        assertEquals(10, contactResponses.size());
        assertEquals(0, webResponse.getCurrentPage());
        assertEquals(10, webResponse.getSize());
        assertEquals(2, webResponse.getTotalPage()); // 9, 19, 29, 39, 49, 59, 69, 79, 89, 90 ... 99 (total 19)
      });

      // query param (phone) with page (1)
      mockMvc.perform(
              get("/api/contacts")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
                      .queryParam("phone", "9")
                      .queryParam("page", "1")
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        PagedResponse<List<ContactResponse>> webResponse = mapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(webResponse);
        List<ContactResponse> contactResponses = webResponse.getData();
        assertFalse(contactResponses.isEmpty());

        assertEquals(9, contactResponses.size());
        assertEquals(1, webResponse.getCurrentPage());
        assertEquals(10, webResponse.getSize());
        assertEquals(2, webResponse.getTotalPage()); // 9, 19, 29, 39, 49, 59, 69, 79, 89, 90 ... 99 (total 19)

      });
    }
  }
}












