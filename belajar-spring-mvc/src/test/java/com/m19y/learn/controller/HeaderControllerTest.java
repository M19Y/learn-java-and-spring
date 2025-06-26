package com.m19y.learn.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class HeaderControllerTest {

  private final MockMvc mockMvc;

  @Autowired
  public HeaderControllerTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  void testHeader() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/header/token").header("X-TOKEN", "Bro")
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString("You are good!"))
    );
  }

  @Test
  @DisplayName(value = "Should provide header with key X-TOKEN, otherwise it will throw 400 bad request")
  void testWithoutHeader() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/header/token")
    ).andExpectAll(
            MockMvcResultMatchers.status().isBadRequest()
    );
  }
}















