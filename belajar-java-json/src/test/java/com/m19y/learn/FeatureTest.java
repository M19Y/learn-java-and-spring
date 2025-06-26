package com.m19y.learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.m19y.learn.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeatureTest {

  @Test
  void withoutUsingConfigure() {

    String json = """
        {"ID":"123", "Name":"Vro"}
        """;

    ObjectMapper mapper = new ObjectMapper();

    JsonProcessingException e = Assertions.assertThrows(JsonProcessingException.class, () ->{
      mapper.readValue(json, Person.class);
    });
    System.out.println(e.getMessage());
  }

  @Test
  void usingConfigure() throws JsonProcessingException {


    String json = """
        {"ID":"123", "Name":"Vro"}
        """;

    // mengaktifkan configure insensitive properties

    // this deprecated
//    ObjectMapper mapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
//    mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);

    // this is not deprecated
    ObjectMapper mapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES).build();


    Person person = mapper.readValue(json, Person.class);

    Assertions.assertEquals("123", person.getId());
    Assertions.assertEquals("Vro", person.getName());
  }
}
