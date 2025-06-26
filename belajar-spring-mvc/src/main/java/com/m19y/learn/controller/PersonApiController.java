package com.m19y.learn.controller;

import com.m19y.learn.model.CreatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonApiController {

  @ResponseBody
  @PostMapping(path = "/api/person",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public CreatePersonRequest createPersonRequest(@RequestBody @Valid CreatePersonRequest request){
    System.out.println(request);
    return request;
  }
}
