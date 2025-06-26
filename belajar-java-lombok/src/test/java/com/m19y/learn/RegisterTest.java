package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterTest {

  @Test
  void testWith() {

    Register register1 = new Register("bro", "nande");
    Register register2 = register1.withUsername("udin");

    Assertions.assertEquals(register1.getPassword(), register2.getPassword());
    Assertions.assertNotEquals(register1.getUsername(), register2.getUsername());
  }
}
