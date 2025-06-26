package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class ConstructorTest {

  @Test
  void create() {

    PersonConstructor person = new PersonConstructor("Otong", "London");
    person.name = "Otong sutorong";

    person.sayHello("Ucup");

  }

  @Test
  void createConstructorOverloading() {
    PersonConstructor person = new PersonConstructor("Otong", "London");
    var person1 = new PersonConstructor();
    var person2 = new PersonConstructor("Udin");
  }
}
