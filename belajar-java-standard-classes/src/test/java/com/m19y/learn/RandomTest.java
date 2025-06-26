package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class RandomTest {

  @Test
  void create() {

    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      System.out.println(random.nextInt(100));
    }
  }
}
