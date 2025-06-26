package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadLocalRandomTest {

  @Test
  void testRandom() throws InterruptedException {

    ExecutorService executor = Executors.newFixedThreadPool(100);

    for (int i = 0; i < 100; i++) {
      executor.execute(() -> {
        try {
          Thread.sleep(1000);
          int number = ThreadLocalRandom.current().nextInt(200);
          System.out.println(number);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    executor.awaitTermination(5, TimeUnit.SECONDS);
  }

  @Test
  void stream() throws InterruptedException {
    ExecutorService executor =Executors.newFixedThreadPool(10);

    executor.execute(() -> {
      ThreadLocalRandom.current().ints(1000, 0, 1000)
              .forEach(System.out::println);
    });

    executor.shutdown();
    executor.awaitTermination(2, TimeUnit.SECONDS);


  }
}
