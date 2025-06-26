package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class ToStringTest {

  @Test
  void create() {
    Product product = new Product("PC", 8_000_000);
    System.out.println(product);
  }
}
