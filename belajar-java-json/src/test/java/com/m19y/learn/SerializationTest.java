package com.m19y.learn;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.m19y.learn.model.Address;
import com.m19y.learn.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.io.IOException;

public class SerializationTest {

  @Test
  void create() throws JsonProcessingException {

    ObjectMapper mapper = JsonMapper.builder()
        .enable(SerializationFeature.INDENT_OUTPUT)
        .build();

    Address address = new Address();
    address.setCity("New York");
    address.setCountry("USA");
    address.setStreet("New York street");

    Person person = new Person();
    person.setAge(8);
    person.setId(UUID.randomUUID().toString());
    person.setName("Abilal");
    person.setAddress(address);
    person.addHobby("Swim");
    person.addHobby("Eat");
    person.addHobby("Coding");

    String json = mapper.writeValueAsString(person);
    System.out.println(json);

    // write to a file
    try(Writer writer = Files.newBufferedWriter(Path.of("person-good.json"))){
      writer.write(json);
      writer.flush();
    }catch (IOException e){
      Assertions.fail(e);
    }
  }

  @Test
  void serializationInclusionTest() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    Person person = new Person();
    person.setId(UUID.randomUUID().toString());
    person.setName("Simple Name");

    String json = objectMapper.writeValueAsString(person);
    System.out.println(json);

  }
}
