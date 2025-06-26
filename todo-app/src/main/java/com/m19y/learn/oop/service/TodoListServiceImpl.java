package com.m19y.learn.oop.service;

import com.m19y.learn.oop.entity.Todo;
import com.m19y.learn.oop.repository.TodoListRepository;

public class TodoListServiceImpl implements TodoListService{

  private final TodoListRepository todoListRepository;

  public TodoListServiceImpl(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  @Override
  public void showTodoList() {
    Todo[] model = todoListRepository.getAll();

    System.out.println("\nTODOLIST");
    for (int i = 0; i < model.length; i++) {

      var todoList = model[i];
      var no = i + 1;
      if (todoList != null){
        System.out.println(no + ". " + todoList.getTodo());
      }
    }
  }

  @Override
  public void addTodoList(String task) {

    Todo todo = new Todo(task);
    todoListRepository.add(todo);
    System.out.println("Success add new todo: " + task);
  }

  @Override
  public void removeTodoList(Integer number) {
    boolean isSuccess = todoListRepository.remove(number);

    if(isSuccess){
      System.out.println("Success to remove todo: " + number);
    }else{
      System.out.println("Failed to remove todo: " + number);
    }
  }
}
