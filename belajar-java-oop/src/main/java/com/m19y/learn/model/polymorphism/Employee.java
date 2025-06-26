package com.m19y.learn.model.polymorphism;

public class Employee {

  public String name;

  public Employee(String name) {
    this.name = name;
  }

  public void sayHallo(String name){
    System.out.println("Hello " + name + ", i'm Employee " + this.name);
  }


}
