package com.m19y.learn;

public class PersonConstructor {

  String name;
  String address;
  private String country = "Indonesia";

  void sayHello(String paramName){
    System.out.println("Hello " + paramName + ", My name is " + name);
  }

  PersonConstructor(String paramName, String paramAddress){
    name = paramName;
    address = paramAddress;
  }

  PersonConstructor(String paramName){
    name = paramName;
  }

  PersonConstructor(){
    this(null);
  }
}
