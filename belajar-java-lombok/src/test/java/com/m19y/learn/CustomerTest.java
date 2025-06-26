package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

class CustomerTest {

  @Test
  void testGetterSetter() {

    Customer customer = new Customer();
    customer.setId("1");
    customer.setName("Bro");

    Assertions.assertEquals("1", customer.getId());
    Assertions.assertEquals("Bro", customer.getName());
  }

  @Test
  void testEqualsAndHashCode() {

    Customer customer1 = new Customer("2", "Sis 1");
    Customer customer2 = new Customer("2", "Sis 2");

    Assertions.assertEquals(customer1, customer2);
    Assertions.assertEquals(customer1.hashCode(), customer2.hashCode());
  }
}