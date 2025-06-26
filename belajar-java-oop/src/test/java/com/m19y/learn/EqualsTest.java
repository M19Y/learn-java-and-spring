package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class EqualsTest {

  @Test
  void create() {

    String first = "Bro";
    first = first + "ther";
    String second = "Brother";
    System.out.println(first == second);
    System.out.println(first.equals(second));
  }
}
