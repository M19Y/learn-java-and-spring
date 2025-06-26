package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

  @Test
  void genericTest() {

    Data<String> dataStr = new Data<>("Simple Data");
    Data<Integer> dataInt = new Data<>(23);

    Assertions.assertEquals("Simple Data", dataStr.data());
    Assertions.assertEquals(23, dataInt.data());
  }
}