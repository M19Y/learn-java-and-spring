package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

  @Test
  void createNewRecord() {

    Customer customer = new Customer("1", "Son Goku", "songoku@gmail.com", "08123123");
    Assertions.assertNotNull(customer);
    System.out.println(customer); // record secara default telah mengimplementasi toString() method

  }

  @Test
  void getProperty() {
    Customer customer = new Customer("1", "Son Goku", "songoku@gmail.com", "08123123");

    Assertions.assertEquals("1", customer.id());
    Assertions.assertEquals("Son Goku", customer.name());
    Assertions.assertEquals("songoku@gmail.com", customer.email());
    Assertions.assertEquals("08123123", customer.phone());
  }

  @Test
  void constructor() {
    Customer customer = new Customer("1", "Son Goku", "songoku@gmail.com");

    Assertions.assertEquals("1", customer.id());
    Assertions.assertEquals("Son Goku", customer.name());
    Assertions.assertEquals("songoku@gmail.com", customer.email());
    Assertions.assertNull(customer.phone());
  }

  @Test
  void canonicalConstructor() {
    Customer customer = new Customer("1", "Son Goku", "SONGOKU@gmail.com");

    Assertions.assertEquals("1", customer.id());
    Assertions.assertEquals("Son Goku", customer.name());
    Assertions.assertEquals("songoku@gmail.com", customer.email());
    Assertions.assertNull(customer.phone());
  }

  @Test
  void recordMethodTest() {
    Customer customer = new Customer("1", "Son Goku", "SONGOKU@gmail.com");

    Assertions.assertEquals("Hello Bro, my name is Son Goku", customer.sayHello("Bro"));
  }

  @Test
  void recordEquals() {
    Customer customer1 = new Customer("1", "Son Goku", "SONGOKU@gmail.com");
    Customer customer2 = new Customer("1", "Son Goku", "SONGOKU@gmail.com");

    Assertions.assertTrue(customer1.equals(customer2));
    Assertions.assertEquals(customer1.hashCode(), customer2.hashCode());
    Assertions.assertEquals(customer1.toString(), customer2.toString());
  }
}
