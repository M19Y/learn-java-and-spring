package com.m19y.learn.repository;

import com.m19y.learn.entity.Todo;

public interface TodoListRepository {

  Todo[] getAll();
  void add(Todo todo);
  boolean remove(Integer number);
}
