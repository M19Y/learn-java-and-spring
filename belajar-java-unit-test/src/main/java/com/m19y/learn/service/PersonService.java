package com.m19y.learn.service;

import com.m19y.learn.data.Person;
import com.m19y.learn.repository.PersonRepository;

import java.util.UUID;

public class PersonService {

  private final PersonRepository repository;

  public PersonService(PersonRepository repository) {
    this.repository = repository;
  }

  public Person get(String id){
    Person person = repository.selectById(id);

    if(person == null){
      throw new IllegalArgumentException("Person not found");
    }

    return person;
  }

  public Person register(String name){
    Person person = new Person(UUID.randomUUID().toString(), name);
//    repository.insert(person);
    repository.insert(person);
    return person;
  }
}
