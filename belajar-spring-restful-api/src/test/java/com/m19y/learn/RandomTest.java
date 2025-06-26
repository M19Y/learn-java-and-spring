package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.Period;

public class RandomTest {

  @Test
  void test30Days() {

    Long first = System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30);
    Long second = Instant.now().plus(Period.ofDays(30)).toEpochMilli();

    Assertions.assertEquals(first, second);
  }
}
