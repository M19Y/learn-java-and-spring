package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

  @Test
  void test() throws InterruptedException {
    final CountDownLatch countDownLatch = new CountDownLatch(5);
    final ExecutorService executor = Executors.newFixedThreadPool(10);


    for (int i = 0; i < 5; i++) {
      executor.execute(() -> {
        try{
          System.out.println("Task start");
          Thread.sleep(2000);
          System.out.println("Task end");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally {
          countDownLatch.countDown();
        }
      });
    }

    executor.execute(() -> {
      try {
        countDownLatch.await();
        System.out.println("Finish all task");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    executor.awaitTermination(10, TimeUnit.SECONDS);

  }
}
