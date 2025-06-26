package com.m19y.learn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m19y.learn.model.CreateAddressRequest;
import com.m19y.learn.model.CreatePersonRequest;
import com.m19y.learn.model.CreateSocialMediaRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createPersonWithJson() throws Exception {

    CreatePersonRequest request = new CreatePersonRequest();
    request.setFirstName("Son");
    request.setMiddleName("Gohan");
    request.setLastName("Bin Son Goku");
    request.setEmail("songohan@gmail.com");
    request.setPhone("+6281234567890");
    request.setAddress(new CreateAddressRequest("simple city", "simple street", "japan", "17"));
    request.setHobbies(new ArrayList<>());
    request.getHobbies().addAll(List.of("Sleeping", "Eating", "Gaming"));
    request.setSocialMedias(new ArrayList<>());
    request.getSocialMedias().addAll(
            List.of(
                    new CreateSocialMediaRequest("Instagram", "instagram.com/sonGohan"),
                    new CreateSocialMediaRequest("Facebook", "facebook.com/sonGohan")
            )
    );

    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
            MockMvcRequestBuilders.post("/api/person")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonRequest)
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString(jsonRequest))
    );

  }

  @Test
  void createPersonError404() throws Exception {

    CreatePersonRequest request = new CreatePersonRequest();
    request.setMiddleName("Gohan");
    request.setLastName("Bin Son Goku");
    request.setAddress(new CreateAddressRequest("simple city", "simple street", "japan", "17"));
    request.setHobbies(new ArrayList<>());
    request.getHobbies().addAll(List.of("Sleeping", "Eating", "Gaming"));
    request.setSocialMedias(new ArrayList<>());
    request.getSocialMedias().addAll(
            List.of(
                    new CreateSocialMediaRequest("Instagram", "instagram.com/sonGohan"),
                    new CreateSocialMediaRequest("Facebook", "facebook.com/sonGohan")
            )
    );

    String jsonRequest = objectMapper.writeValueAsString(request);

    mockMvc.perform(
            MockMvcRequestBuilders.post("/api/person")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(jsonRequest)
    ).andExpectAll(
            MockMvcResultMatchers.status().isBadRequest(),
            MockMvcResultMatchers.content().string(Matchers.containsString("MANVE Validation error"))
    );

  }
}