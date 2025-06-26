package com.m19y.learn.model;

public class Manager{

  public String name;

  public void sayHallo(String name){
    System.out.println("Hello " + name + ", My name is" + this.name);
  }
  public void sayHalloOverriding(String name){
    System.out.println("Hello " + name + ", My name is Manager " + this.name);
  }
}
