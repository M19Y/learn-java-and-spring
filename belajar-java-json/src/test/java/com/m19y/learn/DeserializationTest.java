package com.m19y.learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.m19y.learn.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializationTest {

  @Test
  void createSuccess() throws JsonProcessingException {

    String json = """
        {"id":"1", "height":164, "hobbies":"Learn", "name": "Vro"}
        """;

    ObjectMapper mapper = JsonMapper.builder()
        .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .build();

    Person person = mapper.readValue(json, Person.class);
    Assertions.assertEquals(1, person.getHobbies().size());
    Assertions.assertEquals("1", person.getId());
    Assertions.assertEquals("Vro", person.getName());
  }

  @Test
  void createThrow() {

    String json = """
        {"id":"1", "height":164, "hobbies":"Learn", "name": "Vro"}
        """;

    ObjectMapper mapper = JsonMapper.builder()
//        .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .build();

    Assertions.assertThrows(JsonProcessingException.class, () -> mapper.readValue(json, Person.class));

  }
}
