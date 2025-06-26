package com.m19y.learn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m19y.learn.entity.Address;
import com.m19y.learn.entity.Contact;
import com.m19y.learn.entity.User;
import com.m19y.learn.mapper.AddressMapper;
import com.m19y.learn.model.address.AddressRequest;
import com.m19y.learn.model.address.AddressResponse;
import com.m19y.learn.model.res.web.WebResponseImpl;
import com.m19y.learn.model.web.WebErrorResponse;
import com.m19y.learn.model.web.WebResponse;
import com.m19y.learn.repository.AddressRepository;
import com.m19y.learn.repository.ContactRepository;
import com.m19y.learn.repository.UserRepository;
import com.m19y.learn.security.BCrypt;
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

import java.util.List;
import java.util.Map;
import java.util.UUID;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

  private final MockMvc mockMvc;
  private final ObjectMapper mapper;
  private final ContactRepository contactRepository;
  private final UserRepository userRepository;
  private final AddressRepository addressRepository;
  private final String TOKEN = "my-token";
  private final String CONTACT_ID = "my-contact";

  @Autowired
  public AddressControllerTest(MockMvc mockMvc, ObjectMapper mapper, ContactRepository contactRepository, UserRepository userRepository, AddressRepository addressRepository) {
    this.mockMvc = mockMvc;
    this.mapper = mapper;
    this.contactRepository = contactRepository;
    this.userRepository = userRepository;
    this.addressRepository = addressRepository;
  }

  @BeforeEach
  void setUp() {
    addressRepository.deleteAll();
    contactRepository.deleteAll();
    userRepository.deleteAll();

    User user = new User();
    user.setTokenExpiredAt(System.currentTimeMillis() + 10_000_000);
    user.setToken(TOKEN);
    String USERNAME = "bro";
    user.setUsername(USERNAME);
    user.setName("Bro G");
    user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));

    userRepository.save(user);

    Contact contact = new Contact();
    contact.setId(CONTACT_ID);
    contact.setUser(user);
    contact.setEmail("email@example.com");
    contact.setFirstName("first");
    contact.setLastName("last");

    contactRepository.save(contact);
  }

  @AfterEach
  void tearDown() {
    addressRepository.deleteAll();
    contactRepository.deleteAll();
    userRepository.deleteAll();
  }
  
  @Nested
  class CreateAddressTest{

    @Test
    void create_withEmptyContactId_shouldThrowHttp404() throws Exception{
      AddressRequest request = AddressRequest.builder()
              .city("Tokyo")
              .postalCode("123")
              .province("Tokyo Province")
              .country("Japan")
              .street("Tokyo west street")
              .build();

      // empty id
      mockMvc.perform(
              post("/api/contacts/" + " " + "/addresses")
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {


        WebErrorResponse webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        assertEquals("Contact not found!", webResponse.errors());

      });
    }

    @Test
    void create_withWrongContactId_shouldThrowHttp404() throws Exception{
      AddressRequest request = AddressRequest.builder()
              .city("Tokyo")
              .postalCode("123")
              .province("Tokyo Province")
              .country("Japan")
              .street("Tokyo west street")
              .build();

      // empty id
      mockMvc.perform(
              post("/api/contacts/" + "wrong" + "/addresses")
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {


        WebErrorResponse webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        assertEquals("Contact not found!", webResponse.errors());

      });
    }

    @Test
    void create_withInvalidFieldsRequest_shouldThrowConstraints() throws Exception{
      AddressRequest request = AddressRequest.builder()
              .city("1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111")
              .postalCode("1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111")
              .province("1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111")
              .country("")
              .street("11_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111")
              .build();

      mockMvc.perform(
              post("/api/contacts/" + CONTACT_ID + "/addresses")
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isBadRequest()
      ).andDo(result -> {
        Map<String, String> errorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(errorResponse);

        assertEquals(5, errorResponse.size());
        errorResponse.forEach((k, v) -> log.info("{} --- {}", k, v));
      });
    }

    @Test
    void create_withValidRequest_shouldReturnAddressResponse() throws Exception{
      AddressRequest request = AddressRequest.builder()
              .city("Tokyo")
              .postalCode("123")
              .province("Tokyo Province")
              .country("Japan")
              .street("Tokyo west street")
              .build();

      mockMvc.perform(
              post("/api/contacts/" + CONTACT_ID + "/addresses")
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isCreated()
      ).andDo(result -> {

        String location = result.getResponse().getHeader("Location");
        assertNotNull(location);
        log.info("Header location -> Location: {}", location);

        WebResponseImpl<AddressResponse> webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        AddressResponse addressResponse = webResponse.getData();

        assertNotNull(addressRepository.findById(addressResponse.getId()));

        assertNotNull(addressResponse.getId());
        assertEquals(request.getCity(), addressResponse.getCity());
        assertEquals(request.getStreet(), addressResponse.getStreet());
        assertEquals(request.getCountry(), addressResponse.getCountry());
        assertEquals(request.getProvince(), addressResponse.getProvince());
        assertEquals(request.getPostalCode(), addressResponse.getPostalCode());

      });
    }
  }

  @Nested
  class UpdateAddressTest{

    @Test
    void update_withEmptyContactIdAndAddressId_shouldThrowHttp404() throws Exception{
      AddressRequest request = AddressRequest.builder()
              .city("Tokyo")
              .postalCode("123")
              .province("Tokyo Province")
              .country("Japan")
              .street("Tokyo west street")
              .build();

      // empty id
      mockMvc.perform(
              put("/api/contacts/" + " " + "/addresses/ ")
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {


        WebErrorResponse webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        assertEquals("Contact not found!", webResponse.errors());

      });
    }

    @Test
    void update_withWrongContactIdAndAddressId_shouldThrowHttp404() throws Exception{
      AddressRequest request = AddressRequest.builder()
              .city("Tokyo")
              .postalCode("123")
              .province("Tokyo Province")
              .country("Japan")
              .street("Tokyo west street")
              .build();

      // empty id
      mockMvc.perform(
              put("/api/contacts/" + "wrong" + "/addresses/wrong")
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {


        WebErrorResponse webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        assertEquals("Contact not found!", webResponse.errors());

      });
    }

    @Test
    void update_withInvalidFieldsRequest_shouldThrowConstraints() throws Exception{

      Contact contact = contactRepository.findById(CONTACT_ID).orElse(null);
      assertNotNull(contact);

      final String addressID = UUID.randomUUID().toString();
      Address address = new Address();
      address.setId(addressID);
      address.setCity("city example");
      address.setStreet("street example");
      address.setCountry("country example");
      address.setProvince("province example");
      address.setPostalCode("postal code eample");
      address.setContact(contact);

      addressRepository.save(address);

      AddressRequest request = AddressRequest.builder()
              .city("1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111")
              .postalCode("1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111")
              .province("1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111")
              .country("")
              .street("11_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111_1111111111")
              .build();

      mockMvc.perform(
              put("/api/contacts/" + CONTACT_ID + "/addresses/"+ addressID)
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isBadRequest()
      ).andDo(result -> {
        Map<String, String> errorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(errorResponse);

        assertEquals(5, errorResponse.size());
        errorResponse.forEach((k, v) -> log.info("{} --- {}", k, v));
      });
    }

    @Test
    void update_withValidRequest_shouldReturnAddressResponse() throws Exception{

      Contact contact = contactRepository.findById(CONTACT_ID).orElse(null);
      assertNotNull(contact);

      final String addressID = UUID.randomUUID().toString();
      Address address = new Address();
      address.setId(addressID);
      address.setCity("city example");
      address.setStreet("street example");
      address.setCountry("country example");
      address.setProvince("province example");
      address.setPostalCode("postal code eample");
      address.setContact(contact);

      addressRepository.save(address);

      AddressRequest request = AddressRequest.builder()
              .city("Tokyo")
              .postalCode("123")
              .province("Tokyo Province")
              .country("Japan")
              .street("Tokyo west street")
              .build();

      mockMvc.perform(
              put("/api/contacts/" + CONTACT_ID + "/addresses/"+ addressID)
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isOk()
      ).andDo(result -> {

        WebResponseImpl<AddressResponse> webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        AddressResponse addressResponse = webResponse.getData();

        assertNotNull(addressRepository.findById(addressResponse.getId()));

        assertNotNull(addressResponse.getId());
        assertEquals(request.getCity(), addressResponse.getCity());
        assertEquals(request.getStreet(), addressResponse.getStreet());
        assertEquals(request.getCountry(), addressResponse.getCountry());
        assertEquals(request.getProvince(), addressResponse.getProvince());
        assertEquals(request.getPostalCode(), addressResponse.getPostalCode());

      });
    }

    @Test
    void update_withAddressIdNotExists_shouldThrowHttp404Eng() throws Exception{
      AddressRequest request = AddressRequest.builder()
              .city("Tokyo")
              .postalCode("123")
              .province("Tokyo Province")
              .country("Japan")
              .street("Tokyo west street")
              .build();

      mockMvc.perform(
              put("/api/contacts/" + CONTACT_ID + "/addresses/not-found")
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {

        WebErrorResponse webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        assertEquals("Address not found!", webResponse.errors());

      });
    }

    @Test
    void update_withAddressIdNotExists_shouldThrowHttp404Indonesia() throws Exception{
      AddressRequest request = AddressRequest.builder()
              .city("Tokyo")
              .postalCode("123")
              .province("Tokyo Province")
              .country("Japan")
              .street("Tokyo west street")
              .build();

      mockMvc.perform(
              put("/api/contacts/" + CONTACT_ID + "/addresses/not-found")
                      .accept(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsBytes(request))
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
                      .header("Accept-Language", "id-ID")
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {

        WebErrorResponse webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        assertEquals("Alamat tidak ditemukan!", webResponse.errors());

      });
    }
  }

  @Nested
  class GetAddressByIdTest{

    @Test
    void get_WithNotExistsAddressId_shouldThrowHttp404Eng() throws Exception {
      mockMvc.perform(
              get("/api/contacts/" + CONTACT_ID + "/addresses/not-exists")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        assertEquals("Address not found!", webResponse.errors());
      });
    }

    @Test
    void get_WithNotExistsAddressId_shouldThrowHttp404Indonesia() throws Exception {
      mockMvc.perform(
              get("/api/contacts/" + CONTACT_ID + "/addresses/not-exists")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
                      .header("Accept-Language", "id-ID")
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        assertEquals("Alamat tidak ditemukan!", webResponse.errors());
      });
    }

    @Test
    void get_WithExistingID_shouldReturnAddressResponse() throws Exception {
      Contact contact = contactRepository.findById(CONTACT_ID).orElse(null);
      assertNotNull(contact);

      final String addressID = UUID.randomUUID().toString();
      Address address = new Address();
      address.setId(addressID);
      address.setCity("city example");
      address.setStreet("street example");
      address.setCountry("country example");
      address.setProvince("province example");
      address.setPostalCode("postal code eample");
      address.setContact(contact);

      addressRepository.save(address);

      mockMvc.perform(
              get("/api/contacts/" + CONTACT_ID + "/addresses/"+ addressID)
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        WebResponseImpl<AddressResponse> webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        AddressResponse addressResponse = webResponse.getData();
        assertNotNull(addressResponse);

        assertEquals(address.getId(), addressResponse.getId());
        assertEquals(address.getCity(), addressResponse.getCity());
        assertEquals(address.getStreet(), addressResponse.getStreet());
        assertEquals(address.getCountry(), addressResponse.getCountry());
        assertEquals(address.getProvince(), addressResponse.getProvince());
        assertEquals(address.getPostalCode(), addressResponse.getPostalCode());
      });
    }

  }

  @Nested
  class ListAddressList{


    @Test
    void list_withAnyExistingAddress_shouldReturnListOfAddressResponse() throws Exception{
      Contact contact = contactRepository.findById(CONTACT_ID).orElse(null);
      assertNotNull(contact);

      for (int i = 1; i <= 20; i++) {
        Address address = new Address();
        address.setId("address-id" + i);
        address.setCity("city example");
        address.setStreet("street example");
        address.setCountry("country example");
        address.setProvince("province example");
        address.setPostalCode("postal code eample");
        address.setContact(contact);

        addressRepository.save(address);
      }

      mockMvc.perform(
              get("/api/contacts/" + contact.getId() + "/addresses")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        WebResponse<List<AddressResponse>> webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        List<AddressResponse> addressResponses = webResponse.getData();
        assertFalse(addressResponses.isEmpty());

        assertEquals(20, addressResponses.size());

      });
    }

    @Test
    void list_withEmptyAddresses_shouldReturnListOfAddressResponse() throws Exception{
      mockMvc.perform(
              get("/api/contacts/" + CONTACT_ID + "/addresses")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isOk()
      ).andDo(result -> {
        WebResponse<List<AddressResponse>> webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        List<AddressResponse> addressResponses = webResponse.getData();
        assertTrue(addressResponses.isEmpty());

      });
    }

    @Test
    void list_withContactIdNotFound_shouldThrowHttp404() throws Exception{
      mockMvc.perform(
              get("/api/contacts/wrong-contact-id/addresses")
                      .accept(MediaType.APPLICATION_JSON)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse webResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertNotNull(webResponse);

        assertEquals("Contact not found!", webResponse.errors());

      });
    }
  }
  @Nested
  class RemoveAddressTest{

    @Test
    void remove_withAddressIdNotExist_shouldThrow404Indonesia() throws Exception{

      mockMvc.perform(
              delete("/api/contacts/" + CONTACT_ID + "/addresses/wrong-address")
                      .header("X-API-TOKEN", TOKEN)
                      .header("Accept-Language", "id-ID")
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse webErrorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(webErrorResponse);
        assertEquals("Alamat tidak ditemukan!", webErrorResponse.errors());
      });
    }

    @Test
    void remove_withAddressIdNotExist_shouldThrow404Eng() throws Exception{

      mockMvc.perform(
              delete("/api/contacts/" + CONTACT_ID + "/addresses/wrong-address")
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse webErrorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(webErrorResponse);
        assertEquals("Address not found!", webErrorResponse.errors());
      });
    }

    @Test
    void remove_withContactAndAddressIdNotExist_shouldThrow404Eng() throws Exception{

      mockMvc.perform(
              delete("/api/contacts/wrong-contact/addresses/wrong-address")
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNotFound()
      ).andDo(result -> {
        WebErrorResponse webErrorResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(webErrorResponse);
        assertEquals("Contact not found!", webErrorResponse.errors());
      });
    }

    @Test
    void remove_withAddressIdExist_shouldReturnHttp204() throws Exception{
      Contact contact = contactRepository.findById(CONTACT_ID).orElse(null);
      assertNotNull(contact);

      final String addressID = UUID.randomUUID().toString();
      Address address = new Address();
      address.setId(addressID);
      address.setCity("city example");
      address.setStreet("street example");
      address.setCountry("country example");
      address.setProvince("province example");
      address.setPostalCode("postal code eample");
      address.setContact(contact);

      addressRepository.save(address);

      mockMvc.perform(
              delete("/api/contacts/" + contact.getId() + "/addresses/" + addressID)
                      .header("X-API-TOKEN", TOKEN)
      ).andExpectAll(status().isNoContent());

      // check from dm
      Address addressFromDb = addressRepository.findById(addressID).orElse(null);
      assertNull(addressFromDb);

      // or
      assertFalse(addressRepository.existsById(addressID));
    }
  }
}