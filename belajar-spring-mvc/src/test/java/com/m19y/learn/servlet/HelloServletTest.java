package com.m19y.learn.servlet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloServletTest {

  @LocalServerPort
  private Integer port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testWebServlet() {

    String response = restTemplate.getForObject("/servlet/hello", String.class);
    assertEquals("Hello from servlet", response);
  }
}