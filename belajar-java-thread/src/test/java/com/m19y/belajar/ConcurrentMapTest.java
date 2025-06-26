package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ConcurrentMapTest {

  @Test
  void testMap() throws InterruptedException {

    final ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
    final ExecutorService executor = Executors.newFixedThreadPool(100);
    final CountDownLatch countDownLatch = new CountDownLatch(100);

    for (int i = 0; i < 100; i++) {
      final int index = i;
      executor.execute(() -> {
        try {
          Thread.sleep(1000);
          concurrentMap.put(index, "Data-" + index);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally{
          countDownLatch.countDown();
        }
      });
    }

    executor.execute(() -> {
      try {
        countDownLatch.await();
        concurrentMap.forEach((k, v) -> System.out.println( k + " : " + v));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    executor.awaitTermination(4, TimeUnit.SECONDS);
  }
}
