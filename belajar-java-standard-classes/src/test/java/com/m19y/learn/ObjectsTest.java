package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ObjectsTest {

  @Test
  void withoutUsingObjects() {

    Product product = new Product("Keyboard", 325_000L);
    Product product2 = new Product("Monitor", 1_325_000L);

    if(product != null){
      System.out.println(product.toString());
    }

    if (product != null) {
      System.out.println(product.hashCode());
    }

    if (product != null){
      System.out.println(product.equals(product2));
    }
  }

  @Test
  void usingObjects() {

    Product product = new Product("Keyboard", 325_000L);
    Product product2 = new Product("Monitor", 1_325_000L);

    System.out.println(Objects.toString(product));
    System.out.println(Objects.hashCode(product));
    System.out.println(Objects.equals(product, product2));
  }
}
