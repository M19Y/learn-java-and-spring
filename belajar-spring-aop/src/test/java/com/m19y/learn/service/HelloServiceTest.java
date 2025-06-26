package com.m19y.learn.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloServiceTest {

  @Autowired
  private HelloService helloService;

  @Test
  void testHelloService() {

    String hello = helloService.hello("Vro");
    String hello2 = helloService.hello("Bro", "Son Goku");
    String bye = helloService.bye("Vro");

    assertEquals("Hello Vro", hello);
    assertEquals("Hello Bro Son Goku", hello2);
    assertEquals("Bye Vro", bye);

    helloService.test();


  }
}