package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class ThisKeyTest {

  @Test
  void create() {

    PersonThis person = new PersonThis("otong", "new york");

    System.out.println(person.name);
    System.out.println(person.address);

    person.sayHello("Bro");
  }
}
