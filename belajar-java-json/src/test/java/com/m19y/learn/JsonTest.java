package com.m19y.learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class JsonTest {

  @Test
  void createJson() {

    Map<String, Object> person = Map.of(
        "firstName","Abilal",
        "lastName","bin Udin",
        "age",8,
        "married", false
    );

    ObjectMapper mapper = new ObjectMapper();
    try{
      String json = mapper.writeValueAsString(person);
      System.out.println(json);
    }catch (JsonProcessingException e){
      Assertions.fail(e);
    }
  }

  @Test
  void writeJson() {

    Map<String, Object> person = Map.of(
        "firstName","Abilal",
        "lastName","bin Udin",
        "age",8,
        "married", false
    );

    ObjectMapper mapper = new ObjectMapper();
    try(Writer writer = Files.newBufferedWriter(Path.of("person.json"))){
      String json = mapper.writeValueAsString(person);
      writer.write(json);
      writer.flush();
    }catch (IOException e){
      Assertions.fail(e);
    }
  }

  @Test
  void readJson() {


    ObjectMapper mapper = new ObjectMapper();

    try(InputStream stream = Files.newInputStream(Path.of("person.json"));
        Scanner scanner = new Scanner(stream)){
      StringBuilder builder = new StringBuilder();
      while(scanner.hasNext()){
        builder.append(scanner.nextLine());
      }
      System.out.println(builder);
      Map<String, Object> person = mapper.readValue(builder.toString(), new TypeReference<Map<String, Object>>() {
      });

      Assertions.assertEquals("Abilal", person.get("firstName"));
      Assertions.assertEquals("bin Udin", person.get("lastName"));
      Assertions.assertFalse((Boolean) person.get("married"));
      Assertions.assertEquals(8, person.get("age"));

    }catch (IOException e){
      Assertions.fail(e);
    }
  }
}
