package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {

  @Test
  void testExchangeData() throws InterruptedException {

    // bertukaran data antara satu thread ke thread yang lainnya
    Exchanger<String> exchanger = new Exchanger<>();
    ExecutorService executor = Executors.newFixedThreadPool(10);

    executor.execute(() -> {

      String message = "Banana";
      try {
        System.out.println("Thread 1 send: " + message);
        String data = exchanger.exchange("Message from thread 1: " + message);
        Thread.sleep(2000);
        System.out.println("Thread 1 received: " + data);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });


    executor.execute(() -> {
      String message = "Mango";
      try {
        System.out.println("Thread 2 send: " + message);
        String data = exchanger.exchange("Message from thread 2: " + message);
        Thread.sleep(4000);
        System.out.println("Thread 2 received: " + data);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    executor.awaitTermination(10, TimeUnit.SECONDS);

  }
}
