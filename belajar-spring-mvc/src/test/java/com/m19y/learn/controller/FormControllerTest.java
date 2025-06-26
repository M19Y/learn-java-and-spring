package com.m19y.learn.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class FormControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testCreatePerson() throws Exception{

    mockMvc.perform(
            MockMvcRequestBuilders.post("/form/person")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .param("name", "Son Gohan")
                    .param("birthDate", "2001-09-11")
                    .param("address", "Planet Kaio")
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString(
                    "Success create person with\n name : Son Gohan\n brithDate: 2001-09-11\n address : Planet Kaio\n"
            ))
    );
  }

  @Test
  void testRequestContentType() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders
                    .post("/form/hello")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .param("name","Son Gohan"))
            .andExpectAll(
                    MockMvcResultMatchers.status().isOk(),
                    MockMvcResultMatchers.header().string(
                            HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.TEXT_HTML_VALUE)),
                    MockMvcResultMatchers.content().string(Matchers.containsString("Hello Son Gohan")));
  }
}