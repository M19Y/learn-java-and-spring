package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class SystemTest {

  @Test
  void getEnv() {

    System.getenv().forEach((key, value) -> {
      System.out.printf("%s : %s\n", key, value);
    });

  }

  @Test
  void getSpecificEnv() {
    System.out.println(System.getenv("JAVA_HOME"));
    System.out.println(System.currentTimeMillis());
    System.out.println(System.nanoTime());
  }




}

