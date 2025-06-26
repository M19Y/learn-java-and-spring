package com.m19y.learn.model.polymorphism;

public class Manager extends Employee{

  public Manager(String name) {
    super(name);
  }

  public void sayHallo(String name){
    System.out.println("Hello " + name + ", i'm Manager " + this.name);
  }
}
