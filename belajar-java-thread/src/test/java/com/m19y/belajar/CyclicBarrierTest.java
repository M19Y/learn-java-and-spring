package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class CyclicBarrierTest {

  @Test
  void test() throws InterruptedException {

    final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    final ExecutorService executor = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 4; i++) {
      executor.execute(() -> {
        try {
          System.out.println("Waiting");
          cyclicBarrier.await();
          System.out.println("Done waiting");
        } catch (InterruptedException | BrokenBarrierException e) {
          throw new RuntimeException(e);
        }
      });
    }

    executor.awaitTermination(10, TimeUnit.SECONDS);
  }
}
