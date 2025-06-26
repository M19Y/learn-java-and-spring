package com.m19y.learn.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class PartnerControllerTest {

  @Autowired
  private MockMvc mockMvc;


  @Test
  void testPartnerResolver() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/partner/current")
                    .header("X-API-KEY", "Son Gohan")
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString("Son Gohan : sample partner"))
    );
  }

  @Test
  void testPartnerResolverError () throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/partner/current")
    ).andExpectAll(
            MockMvcResultMatchers.status().isUnauthorized()
    );
  }

}