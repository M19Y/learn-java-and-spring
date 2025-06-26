package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.util.Base64;

public class Base64Test {

  @Test
  void create() {

    String simple = "Learn Java Standard Classes";
    String encode = Base64.getEncoder().encodeToString(simple.getBytes());
    System.out.println(encode);

    byte[] bytes = Base64.getDecoder().decode(encode);
    String decode = new String(bytes);
    System.out.println(decode);

  }
}
