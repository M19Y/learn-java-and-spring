package com.m19y.belajar;

import org.junit.jupiter.api.Test;

public class AtomicTest {

  @Test
  void counter() throws InterruptedException {
    CounterAtomic counter = new CounterAtomic();
    Runnable runnable = () -> {
      for (int i = 0; i < 1_000_000; i++) {
        counter.increment();
      }
    };

    /*
    runnable.run();
    runnable.run();
    runnable.run();
    */

    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    Thread thread3 = new Thread(runnable);

    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();

    System.out.println(counter.getValue());
  }
}
