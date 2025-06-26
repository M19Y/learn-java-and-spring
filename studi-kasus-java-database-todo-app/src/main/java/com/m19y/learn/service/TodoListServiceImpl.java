package com.m19y.learn.service;

import com.m19y.learn.entity.Todo;
import com.m19y.learn.repository.TodoListRepository;

public class TodoListServiceImpl implements TodoListService{

  private final TodoListRepository todoListRepository;

  public TodoListServiceImpl(TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }

  @Override
  public void showTodoList() {
    Todo[] model = todoListRepository.getAll();

    System.out.println("\nTODOLIST");
    for (Todo todoList : model) {
      if (todoList != null) {
        System.out.println(todoList.getId() + ". " + todoList.getTodo());
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
