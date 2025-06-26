package com.m19y.learn;

public class PersonMethod {

  String name;
  String address;
  private String country = "Indonesia";

  void sayHello(String paramName){
    System.out.println("Hello " + paramName + ", My name is " + name);
  }
}
