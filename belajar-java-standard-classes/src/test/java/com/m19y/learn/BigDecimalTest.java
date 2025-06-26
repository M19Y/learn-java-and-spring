package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalTest {

  @Test
  void create() {

    BigDecimal price1 = new BigDecimal("100000000000000000000000.00000000000");
    long simple1 = 1000_000_000_000_000_000L;
    System.out.println(price1);
    System.out.println(simple1);

    BigInteger price2 = new BigInteger("100000000000000000000000");
    long simple2 = 1000_000_000_000_000_000L;
    System.out.println(price2);
    System.out.println(simple2);

    Assertions.assertThrows(NumberFormatException.class, () -> new BigInteger("100000000000000000000000.0"));
  }

  @Test
  void Operation() {
    BigInteger price = new BigInteger("10");
    BigInteger addPrice = price.add(new BigInteger("20"));
    System.out.println(price);
    System.out.println(addPrice);

  }
}



