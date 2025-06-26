package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringArrayTest {

  @Test
  void isNull() {

    String[] array = new String[10];

    array[0] = "Simple 1";
    array[1] = "Simple 2";

    Assertions.assertNull(array[2]); // mungkin bisa null
    Assertions.assertNotNull(array); // selalu tidak null
  }
}
