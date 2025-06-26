package com.m19y.learn.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void helloGuestTest() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpectAll(
                    MockMvcResultMatchers.status().isOk(),
                    MockMvcResultMatchers.content().string(Matchers.containsString("Hello Guest"))
            );
  }

  @Test
  void helloNameTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/hello").queryParam("name", "Son Goku"))
            .andExpectAll(
                    MockMvcResultMatchers.status().isOk(),
                    MockMvcResultMatchers.content().string(Matchers.containsString("Hello Son Goku"))
            );
  }

  @Test
  void helloMethodNotAllowed() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/hello").queryParam("name", "Son Goku"))
            .andExpectAll(
                    MockMvcResultMatchers.status().isMethodNotAllowed()
            );
  }
}
