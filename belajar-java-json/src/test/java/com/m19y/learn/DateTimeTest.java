package com.m19y.learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.m19y.learn.model.Person;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeTest {

  @Test
  void createDefault() throws JsonProcessingException {

    Person person = new Person();
    person.setName("Simple");
    person.setCreatedAt(new Date());
    person.setUpdatedAt(new Date());

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(person);
    System.out.println(json);

  }

  @Test
  void withConfigure() throws JsonProcessingException {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy hh:mm:ss");

    ObjectMapper mapper = new ObjectMapper();
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.setDateFormat(simpleDateFormat);

    Person person = new Person();
    person.setName("Simple");
    person.setCreatedAt(new Date());
    person.setUpdatedAt(new Date());


    String json = mapper.writeValueAsString(person);
    System.out.println(json);
  }
}
