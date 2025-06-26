package com.m19y.learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class JsonArrayTest {

  @Test
  void write() throws JsonProcessingException {

    List<String> names = List.of("Apon", "Abilal", "Adam", "Jait", "Fatih");

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(names);
    System.out.println(json);
  }

  @Test
  void read() throws JsonProcessingException {

    String names = """
        ["Apon","Abilal","Adam","Jait","Fatih"]
        """;

    ObjectMapper mapper = new ObjectMapper();
    List<String> jsonList = mapper.readValue(names, new TypeReference<List<String>>() {
    });

    Assertions.assertEquals(5, jsonList.size());
    Assertions.assertEquals(List.of("Apon", "Abilal", "Adam", "Jait", "Fatih"), jsonList);
  }
}
