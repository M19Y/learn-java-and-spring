package com.m19y.practice;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

  @Test
  void testExecutorService() throws InterruptedException {

    ExecutorService executor = Executors.newSingleThreadExecutor();

    for (int i = 0; i < 100; i++) {
      executor.execute(() -> {
        try{
          Thread.sleep(1000);
          System.out.println("Runnable in thread => " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    executor.awaitTermination(1, TimeUnit.DAYS);

  }

  @Test
  void testExecutorServiceFix() throws InterruptedException {

    ExecutorService executor = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 100; i++) {
      executor.execute(() -> {
        try{
          Thread.sleep(1000);
          System.out.println("Runnable in thread => " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.DAYS);

  }

  @Test
  void testExecutorServiceCached() throws InterruptedException {

    ExecutorService executor = Executors.newCachedThreadPool();

    for (int i = 0; i < 100; i++) {
      executor.execute(() -> {
        try{
          Thread.sleep(1000);
          System.out.println("Runnable in thread => " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.DAYS);

  }

}
