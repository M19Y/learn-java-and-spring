package com.m19y.learn.model.polymorphism;

public class VicePresident extends Manager {
  public VicePresident(String name) {
    super(name);
  }

  public void sayHallo(String name){
    System.out.println("Hello " + name + ", i'm VicePresident " + this.name);
  }
}
