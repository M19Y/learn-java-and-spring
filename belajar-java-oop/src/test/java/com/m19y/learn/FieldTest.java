package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class FieldTest {

  @Test
  void manipulation() {
    PersonField person = new PersonField();
    person.name = "otong";
    person.address = "new york";
//    person.country = "USA"; // kita tidak bisa mengubah country karena final

    System.out.println(person.name);
    System.out.println(person.address);
    System.out.println(person.country);
  }
}
