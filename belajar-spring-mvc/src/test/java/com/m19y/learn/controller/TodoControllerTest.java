package com.m19y.learn.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Order(value = 2)
  @Test
  void testGetTodo() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.get("/todos")
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString("Eat"))
    );
  }

  // 1
  @Order(value = 1)
  @Test
  void testAddTodo() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.post("/todos")
                    .param("todo", "Eat")
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString("Eat"))
    );
  }
}