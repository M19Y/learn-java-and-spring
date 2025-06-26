package com.m19y.learn.oop.repository;

import com.m19y.learn.oop.entity.Todo;

public interface TodoListRepository {

  Todo[] getAll();
  void add(Todo todo);
  boolean remove(Integer number);
}
