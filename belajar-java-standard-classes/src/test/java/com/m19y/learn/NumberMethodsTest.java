package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberMethodsTest {

  @Test
  void conversion() {

    Long longNum = 127L;
    Integer intNum = longNum.intValue();
    Short shortNum = intNum.shortValue();
    Byte byteNum = shortNum.byteValue();

    // floating point
    Double doubleNum = byteNum.doubleValue();
    Float floatNum = doubleNum.floatValue();

    Assertions.assertNotEquals(doubleNum, floatNum); // walaupun nilainya sama tapi jika objectnya beda maka akan beda juga
    Assertions.assertEquals(doubleNum.floatValue(), floatNum);
    Assertions.assertEquals(longNum.byteValue(), byteNum);

  }

  @Test
  void stringToPrimitive() {

    int intNum = Integer.parseInt("10");
    Long longNum1 = Long.valueOf("100");
    Long longNum2 = Long.parseLong("100");

    Assertions.assertEquals(longNum1, longNum2);
  }
}
