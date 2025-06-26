package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

  List<String> hobbies = List.of("Eat", "Sleep", "Code");

  @Test
  void testCreateUsingConstructor() {
    Person person = new Person("1", "Bro", 23, hobbies);
  }

  @Test
  void testCreateUsingSetter() {
    Person person = new Person();
    person.setId("2");
    person.setName("Sis");
    person.setAge(15);
    person.setHobbies(hobbies);
  }

  @Test
  void testCreateUsingBuilder() {

    Person person = Person.builder()
            .name("Unc")
            .age(42)
            .id("3")
            .hobbies(hobbies)
            .build();
  }

  @Test
  void testCreateUsingBuilderWithSingular() {

    Person person = Person.builder()
            .name("Unc")
            .age(42)
            .id("3")
            .hobby("Eat").hobby("Sleep").hobby("Code")
            .build();
  }
}