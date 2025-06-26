package com.m19y.learn.oop.service;

import com.m19y.learn.oop.entity.Todo;
import com.m19y.learn.oop.repository.TodoListRepository;
import com.m19y.learn.oop.repository.TodoListRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoListServiceTest {

  private TodoListRepositoryImpl todoListRepository;
  private TodoListService todoListService;
  @BeforeEach
  void setUp() {
    todoListRepository = new TodoListRepositoryImpl();
    todoListService = new TodoListServiceImpl(todoListRepository);

    todoListRepository.data[0] = new Todo("Task 1");
    todoListRepository.data[1] = new Todo("Task 2");
    todoListRepository.data[2] = new Todo("Task 3");
  }

  @Test
  void showTodoList() {
    todoListService.showTodoList();
  }

  @Test
  void addTodoList() {
    todoListService.addTodoList("Simple 1");
    todoListService.addTodoList("Simple 2");

    todoListService.showTodoList();

    Assertions.assertEquals(5, todoListRepository.totalItems());
  }

  @Test
  void successRemoveTodoList() {
    todoListService.removeTodoList(3);

    Assertions.assertEquals(2, todoListRepository.totalItems());

  }

  @Test
  void failedRemoveTodoList() {
    todoListService.removeTodoList(4);

    Assertions.assertEquals(3, todoListRepository.totalItems());

  }
}
