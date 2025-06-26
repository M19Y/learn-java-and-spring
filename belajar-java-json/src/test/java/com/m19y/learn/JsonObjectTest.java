package com.m19y.learn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m19y.learn.model.Address;
import com.m19y.learn.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class JsonObjectTest {

  @Test
  void createJsonFromObject() throws JsonProcessingException {

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

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(person);
    System.out.println(json);
  }

  @Test
  void readJsonFromObject() throws JsonProcessingException {

    String json = """
        {
        "id":"240fd4ba-df81-44ec-85c9-5ac8f661f0f1",
        "name":"Abilal",
        "age":8,
        "married":false,
        "hobbies":["Swim","Eat","Coding"],
        "address": {"city":"New York",
                    "street":"New York street",
                    "country":"USA"}
        }
        """;

    ObjectMapper mapper = new ObjectMapper();
    Person person = mapper.readValue(json, Person.class);

    Assertions.assertNotNull(person.getId());
    Assertions.assertEquals("Abilal", person.getName());
    Assertions.assertEquals(8, person.getAge());
    Assertions.assertEquals(List.of("Swim","Eat","Coding"), person.getHobbies());
    Assertions.assertEquals("New York", person.getAddress().getCity());
    Assertions.assertEquals("New York street", person.getAddress().getStreet());
    Assertions.assertEquals("USA", person.getAddress().getCountry());


  }

  @Test
  void errorReadJsonFromObject(){

    // error
    String json = """
        {
        "id":"240fd4ba-df81-44ec-85c9-5ac8f661f0f1",
        "name":"Abilal",
        "age":8,
        "married":false,
        "hobbies":["Swim","Eat","Coding"],
        "address": {"city":"New York",
                    "street":"New York street",
                    "country":"USA"}
        }
        """;

    ObjectMapper mapper = new ObjectMapper();

    Assertions.assertThrows(JsonProcessingException.class,
        () -> mapper.readValue(json, Person.class));

  }
}
