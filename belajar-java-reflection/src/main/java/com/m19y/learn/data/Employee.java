package com.m19y.learn.data;

public class Employee implements Nameable{

  private String id;
  private String name;

  public Employee() {
  }

  public Employee(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return this.id;
  }
}
