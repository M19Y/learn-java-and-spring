package com.m19y.learn.controller;

import com.m19y.learn.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getUser() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/auth/user")
                    .cookie(new Cookie("username", URLEncoder.encode("Son Gohan", StandardCharsets.UTF_8)))
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString("Hello Son Gohan"))
    );
  }

  @Test
  void testLoginSuccess() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/login")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .param("username", "Son Gohan")
                    .param("password", "Secret")
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString("Valid Credential")),
            MockMvcResultMatchers.cookie().value("username",
                    Matchers.is(URLEncoder.encode("Son Gohan", StandardCharsets.UTF_8)))
    ).andExpect(result -> {
            // test http session-nya
            HttpSession session = result.getRequest().getSession(false);
            assertNotNull(session);
            User user = (User) session.getAttribute("user");
            assertEquals("Son Gohan", user.getUsername());
    });
  }

  @Test
  void testLoginInvalid() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/login")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .param("username", "Bro")
                    .param("password", "Secret")
    ).andExpectAll(
            MockMvcResultMatchers.status().isUnauthorized(),
            MockMvcResultMatchers.content().string(Matchers.containsString("Invalid Credential"))
    );
  }
}