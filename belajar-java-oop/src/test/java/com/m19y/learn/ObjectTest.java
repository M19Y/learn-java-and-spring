package com.m19y.learn;

import com.m19y.learn.model.Person;
import org.junit.jupiter.api.Test;

public class ObjectTest {

  @Test
  void createObject() {

    Person person = new Person();
    Person person1;
    person1 = new Person();
    var person2 = new Person();
  }
}
