package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class RuntimeTest {

  @Test
  void create() {

    System.out.println(Runtime.getRuntime().availableProcessors());
    System.out.println(Runtime.getRuntime().freeMemory());
    System.out.println(Runtime.getRuntime().maxMemory());
    System.out.println(Runtime.getRuntime().totalMemory());

  }
}
