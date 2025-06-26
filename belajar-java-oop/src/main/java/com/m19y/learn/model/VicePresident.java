package com.m19y.learn.model;

public class VicePresident extends Manager{

  // this is method overriding
  public void sayHalloOverriding(final String name){
    System.out.println("Hello " + name + ", My name is VicePresident " + this.name);
  }
}
