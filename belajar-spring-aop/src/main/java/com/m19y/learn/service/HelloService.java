package com.m19y.learn.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloService {

  public String hello(String name){
    log.info("Call HelloService.hello() with 1 params");
    return "Hello " + name;
  }

  public String hello(String firstName, String lastName){
    log.info("Call HelloService.hello() with 2 params");
    return "Hello " + firstName + " " + lastName;
  }

  public String bye(String name){
    log.info("Call HelloService.bye()");
    return "Bye " + name;
  }

  public void test(){
    log.info("Call HelloService.test()");
  }
}
