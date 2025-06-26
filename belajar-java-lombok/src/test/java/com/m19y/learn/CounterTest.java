package com.m19y.learn;

import org.junit.jupiter.api.Test;

class CounterTest {

  private Counter counter = new Counter();
  @Test
  void testSynchronized() throws InterruptedException {
    for (int i = 0; i < 100; i++) {
      new Thread(() -> {
        for (int j = 0; j < 100; j++) {
          counter.increment();
        }
      }).start();
    }

    Thread.sleep(3_000);
    System.out.println(counter.getCounter());
  }
}