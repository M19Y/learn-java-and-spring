package com.m19y.learn.helper;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
public class SayHello implements ISayHello{

  @Override
  public String sayHello(String name) {
    return "Hello " + name;
  }
}
