package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class MethodTest {

  @Test
  void invokeMethodFromClass() {

    PersonMethod person = new PersonMethod();
    person.name = "James";

    person.sayHello("ronaldo");
  }
}
