package com.m19y.learn;

import com.m19y.learn.helper.SayHello;
import com.m19y.learn.helper.StringHelper;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SayHelloTest {

  @Autowired
  SayHello sayHello;

  @Test
  void validTest() {
    String message = sayHello.sayHello("Bro");
    Assertions.assertEquals("Hello Bro", message);
  }

  @Test
  void invalidTest() {
    ConstraintViolationException ex = Assertions.assertThrows(ConstraintViolationException.class, () -> {
      sayHello.sayHello("");
    });

    System.out.println(ex.getMessage());
//    Assertions.assertEquals("name tidak boleh blank", ex.getMessage());
  }
}
