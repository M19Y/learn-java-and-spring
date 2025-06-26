package com.m19y.learn.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

  @Test
  void testRecord() {

    Class<Product> productClass = Product.class;

    Assertions.assertTrue(productClass.isRecord());
    Assertions.assertEquals(3, productClass.getDeclaredFields().length);
    Assertions.assertEquals(6, productClass.getDeclaredMethods().length);
    Assertions.assertEquals(Record.class, productClass.getSuperclass());
    Assertions.assertEquals(1, productClass.getDeclaredConstructors().length);

    System.out.println(Arrays.toString(productClass.getRecordComponents()));
  }

  @Test
  void testInvokeRecordMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    Class<Product> productClass = Product.class;

    Method name = productClass.getMethod("name");

    Product product = new Product("1", "rexus", 126_000L);

    String productName = (String) name.invoke(product);

    Assertions.assertEquals(product.name(), productName);
  }

  @Test
  void testGetRecordByComponents() throws InvocationTargetException, IllegalAccessException {

    Class<Product> productClass = Product.class;

    RecordComponent[] recordComponents = productClass.getRecordComponents();

    Product product = new Product("2", "Atom81", 260_000L);
    for (RecordComponent component : recordComponents) {
      Method accessor = component.getAccessor();
      System.out.println(accessor);
      System.out.println(accessor.getName());
      System.out.println(accessor.invoke(product));
      System.out.println("========\n");
    }

  }
}