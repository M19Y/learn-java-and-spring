package com.m19y.belajar;

import org.junit.jupiter.api.Test;

public class SynchronizedTest {
  @Test
  void syncMethodCounter() throws InterruptedException {

    SynchronizedCounter counter = new SynchronizedCounter();
    Runnable runnable = () -> {
      for (int i = 0; i < 1_00; i++) {
        counter.syncMethodIncrement();
      }
    };

    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    Thread thread3 = new Thread(runnable);

    thread1.setName("bagian 1");
    thread2.setName("bagian 2");
    thread3.setName("bagian 3");

    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();

    System.out.println(counter.getValue());
  }

  @Test
  void syncStatementCounter() throws InterruptedException {

    SynchronizedCounter counter = new SynchronizedCounter();
    Runnable runnable = () -> {
      for (int i = 0; i < 1_00; i++) {
        counter.syncStatementIncrement();
      }
    };

    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    Thread thread3 = new Thread(runnable);

    thread1.setName("bagian 1");
    thread2.setName("bagian 2");
    thread3.setName("bagian 3");

    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();

    System.out.println(counter.getValue());
  }
}
