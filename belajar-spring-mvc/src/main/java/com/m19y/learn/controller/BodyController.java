package com.m19y.learn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m19y.learn.model.HelloRequest;
import com.m19y.learn.model.HelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BodyController {

  @Autowired
  private ObjectMapper objectMapper;

  @ResponseBody
  @PostMapping(path = "/body/hello",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public String hello(@RequestBody String requestBody) throws JsonProcessingException {
    HelloRequest helloRequest = objectMapper.readValue(requestBody, HelloRequest.class);

    HelloResponse helloResponse =  new HelloResponse();
    helloResponse.setHello("Hello " + helloRequest.getName());
    return objectMapper.writer().writeValueAsString(helloResponse);
  }
}
