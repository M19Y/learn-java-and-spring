package com.m19y.learn.repository;

import com.m19y.learn.data.Person;

public interface PersonRepository {

  Person selectById(String id);

  void insert(Person person);
}
