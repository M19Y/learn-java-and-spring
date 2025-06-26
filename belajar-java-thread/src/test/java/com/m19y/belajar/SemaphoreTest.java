package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {


  @Test
  void semaphoreTest() throws InterruptedException {
    Semaphore semaphore = new Semaphore(10);
    ExecutorService executor = Executors.newFixedThreadPool(100);

    for (int i = 0; i < 1_000; i++) {
      executor.execute(() -> {
        try{
          semaphore.acquire(); // akan membatasi thread pada saat akan mengkesekusi program
          Thread.sleep(1000);
          System.out.println("finish");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally {
          semaphore.release();
        }
      });
    }

    executor.awaitTermination(12, TimeUnit.SECONDS);

  }
}
