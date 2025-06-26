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
public class ViewHelloControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testViewHelloMustache() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.get("/web/hello")
                    .param("name", "Son Gohan")
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString("Belajar View")),
            MockMvcResultMatchers.content().string(Matchers.containsString("Hello Son Gohan"))
    );
  }

  @Test
  void testViewHelloMustacheRedirect() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.get("/web/hello")
    ).andExpectAll(
            MockMvcResultMatchers.status().is3xxRedirection()
    );
  }
}
