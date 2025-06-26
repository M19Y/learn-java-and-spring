package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

  @Test
  void testDataWithOverride() {
    Product product = new Product("123");
    product.setName("Iphone");
    product.setPrice(10_000_000L);

    Assertions.assertEquals("Product(id=123, name=Iphone)", product.toString());
  }
}
