package com.m19y.learn.controller;

import com.m19y.learn.model.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testSession() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.get("/user/current")
                    .sessionAttr("user", new User("Son Gohan"))
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString("Hello Son Gohan"))
    );
  }

  @Test
  void testSessionInterceptorInvalid() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/user/current")
    ).andExpectAll(
            MockMvcResultMatchers.status().is3xxRedirection()
    );
  }
}