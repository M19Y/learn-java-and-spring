package com.m19y.learn;

public class PersonThis {

  String name;
  String address;
  private String country = "Indonesia";

  void sayHello(String name){
    System.out.println("Hello " + name + ", My name is " + this.name);
  }

  PersonThis(String name, String address){
    this.name = name;
    this.address = address;
  }

  PersonThis(String name){
    this.name = name;
  }

  PersonThis(){
    this(null);
  }
}
