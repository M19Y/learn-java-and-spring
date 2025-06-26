package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.RecordComponent;

public class ReflectionTest {

  @Test
  void reflectionOnRecordTest() {

    Assertions.assertTrue(Customer.class.isRecord());
    RecordComponent[] recordComponents = Point.class.getRecordComponents();
    Assertions.assertEquals(2, recordComponents.length); // x and y

    for (RecordComponent component : recordComponents) {
      System.out.println(component.getName());
    }
  }
}
