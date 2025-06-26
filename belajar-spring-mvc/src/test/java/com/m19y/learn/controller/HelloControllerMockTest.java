package com.m19y.learn.controller;

import com.m19y.learn.service.HelloService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerMockTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private HelloService helloService;

  @Test
  void helloGuestTest() throws Exception {
    Mockito.when(helloService.hello(Mockito.isNull())).thenReturn("Hello Hello Bandung");
    mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
            .andExpectAll(
                    MockMvcResultMatchers.status().isOk(),
                    MockMvcResultMatchers.content().string(Matchers.containsString("Hello Hello Bandung"))
            );
  }

  @Test
  void helloNameTest() throws Exception {
    Mockito.when(helloService.hello(Mockito.anyString()))
            .thenReturn("Hello Hello Bandung");
    mockMvc.perform(MockMvcRequestBuilders.get("/hello").queryParam("name", "Son Goku"))
            .andExpectAll(
                    MockMvcResultMatchers.status().isOk(),
                    MockMvcResultMatchers.content().string(Matchers.containsString("Hello Hello Bandung"))
            );
  }
}
