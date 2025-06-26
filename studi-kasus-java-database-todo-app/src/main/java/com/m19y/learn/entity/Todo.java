package com.m19y.learn.entity;

public class Todo {

  private Integer id;
  private String todo;

  public Todo() {
  }

  public Todo(String todo) {
    this.todo = todo;
  }

  public String getTodo() {
    return todo;
  }

  public void setTodo(String todo) {
    this.todo = todo;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
