package com.m19y.learn.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestTemplateTest {

  @LocalServerPort
  private Integer port;

  @Autowired
  private RestTemplate restTemplate;


  @Test
  @Order(1)
  void testAddTodo() {

    final String URL = "http://localhost:" + port + "/todos";

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
    form.add("todo", "Learn Spring MVC");

    RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(
      form, headers, HttpMethod.POST, URI.create(URL)
    );


    ResponseEntity<List<String>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<String>>() {});

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertNotNull(response.getBody());
    Assertions.assertEquals("Learn Spring MVC", response.getBody().getFirst());

  }

  @Test
  @Order(2)
  void testGetTodos() {

    final String URL = "http://localhost:" + port + "/todos";

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));

    RequestEntity<Object> request = new RequestEntity<>(
            headers, HttpMethod.GET, URI.create(URL)
    );

    ResponseEntity<List<String>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<String>>() {});

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertNotNull(response.getBody());
    Assertions.assertEquals("Learn Spring MVC", response.getBody().getFirst());

  }
}
