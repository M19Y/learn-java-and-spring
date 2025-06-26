package com.m19y.learn.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createPersonTest() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.post("/person/create")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .param("firstName", "Son")
                    .param("middleName", "Gohan")
                    .param("lastName", "Bin Son Goku")
                    .param("email", "songohan@gmail.com")
                    .param("phone", "+6281234567890")
                    .param("address.city", "Saint Seiya city")
                    .param("address.street", "Kali x street")
                    .param("address.country", "Japan")
                    .param("address.postalCode", "17")
                    .param("hobbies[0]", "eating")
                    .param("hobbies[1]", "sleeping")
                    .param("hobbies[2]", "fight")
                    .param("socialMedias[0].name", "facebook")
                    .param("socialMedias[0].location", "facebook.com/SonGohan")
                    .param("socialMedias[1].name", "instagram")
                    .param("socialMedias[1].location", "instagram.com/SonGohan")
    ).andExpectAll(
            MockMvcResultMatchers.status().isCreated(),
            MockMvcResultMatchers.content().string(Matchers.containsString(
                    "Success create person Son Gohan Bin Son Goku With Email songohan@gmail.com " +
                            "And Phone +6281234567890 " +
                            "With Address Saint Seiya city, Kali x street, Japan, 17"
            ))
    );
  }

  @Test
  void createPerson400Test() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.post("/person/create")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .param("middleName", "Gohan")
                    .param("lastName", "Bin Son Goku")
                    .param("email", "songohan@gmail.com")
                    .param("phone", "+6281234567890")
                    .param("address.city", "Saint Seiya city")
                    .param("address.street", "Kali x street")
                    .param("address.country", "Japan")
                    .param("address.postalCode", "17")
                    .param("hobbies[0]", "eating")
                    .param("hobbies[1]", "sleeping")
                    .param("hobbies[2]", "fight")
                    .param("socialMedias[0].name", "facebook")
                    .param("socialMedias[0].location", "facebook.com/SonGohan")
                    .param("socialMedias[1].name", "instagram")
                    .param("socialMedias[1].location", "instagram.com/SonGohan")
    ).andExpectAll(
            MockMvcResultMatchers.status().isBadRequest(),
            MockMvcResultMatchers.content().string(Matchers.containsString("You send invalid data"))
    );
  }
}

