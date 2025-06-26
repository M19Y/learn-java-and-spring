package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {


  @Test
  void create() {

    for (int i = 0; i < 10; i++) {
      System.out.println(UUID.randomUUID());
    }
  }
}

