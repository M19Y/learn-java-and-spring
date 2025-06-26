package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class StringMethodsTest {

  String simple = "Learn Java Standard Classes";

  @Test
  void methodsTest() {

    System.out.println(simple.toUpperCase());
    System.out.println(simple.toLowerCase());
    System.out.println(simple.length());

    System.out.println(simple.startsWith("Learn"));
    System.out.println(simple.startsWith("Java"));

    System.out.println(simple.endsWith("Classes"));
    System.out.println(simple.endsWith("Standard"));

    String[] split = simple.split(" ");

    for(String data: split){
      System.out.println(data);
    }
  }


}
