package com.m19y.learn;

public class MangerSuper {

  String name;

  MangerSuper(String name) {
    this.name = name;
  }

  void sayHallo(String name){
    System.out.println("Hello " + name + ", My name is Manager " + this.name);
  }
}
