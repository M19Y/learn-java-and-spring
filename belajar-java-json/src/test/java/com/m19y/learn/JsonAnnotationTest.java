package com.m19y.learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m19y.learn.model.Person;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class JsonAnnotationTest {

  @Test
  void createDefault() throws JsonProcessingException {

    Person person = new Person();
    person.setName("Simple");
    person.setCreatedAt(new Date());
    person.setUpdatedAt(new Date());
    person.setFullName("Bro gooks");
    person.setPassword("secret");

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(person);
    System.out.println(json);

  }
}
