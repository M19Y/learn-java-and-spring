package com.m19y.learn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m19y.learn.model.HelloRequest;
import com.m19y.learn.model.HelloResponse;
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
class BodyControllerTest {

  private final MockMvc mockMvc;
  private final ObjectMapper objectMapper;

  @Autowired
  public BodyControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
  }

  @Test
  void testRequestBody() throws Exception {
    HelloRequest helloRequest = new HelloRequest();
    helloRequest.setName("Gohan");
    mockMvc.perform(
            MockMvcRequestBuilders.post("/body/hello")
                    .content(objectMapper.writeValueAsString(helloRequest))
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk()
    ).andExpect(result -> {
      String response = result.getResponse().getContentAsString();
      HelloResponse helloResponse = objectMapper.readValue(response, HelloResponse.class);
      assertEquals("Hello Gohan", helloResponse.getHello());
    });
  }
}