package com.m19y.learn.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIntegrationTest {

  @LocalServerPort
  private Integer port;

  @Autowired
  private TestRestTemplate testRestTemplate;


  @Test
  void helloGuestTest() {

    String message = testRestTemplate.getForEntity("http://localhost:" + port + "/hello", String.class).getBody();

    Assertions.assertNotNull(message);
    Assertions.assertEquals("Hello Guest", message.trim());
  }

  @Test
  void helloNameTest() {

    String message = testRestTemplate.getForEntity("http://localhost:" + port + "/hello?name=Son Goku", String.class).getBody();

    Assertions.assertNotNull(message);
    Assertions.assertEquals("Hello Son Goku", message.trim());
  }
}
