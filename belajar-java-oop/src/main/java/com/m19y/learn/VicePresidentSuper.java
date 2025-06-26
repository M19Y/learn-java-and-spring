package com.m19y.learn;

public class VicePresidentSuper extends MangerSuper {
  VicePresidentSuper(String name) {
    super(name);
  }

  void sayHallo(String name) {
    System.out.println("Hello " + name + ", My name is VP " + this.name);
  }
}
