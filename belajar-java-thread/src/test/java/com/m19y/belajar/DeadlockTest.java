package com.m19y.belajar;

import org.junit.jupiter.api.Test;

public class DeadlockTest {

  @Test
  void transferDeadlock() throws InterruptedException {

    Balance balance1 = new Balance(1_000_000L);
    Balance balance2 = new Balance(1_000_000L);

    Thread thread1 = new Thread(() -> {
      try {
        Balance.transferDeadlock(balance1, balance2, 500_000L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    Thread thread2 = new Thread(() -> {
      try {
        Balance.transferDeadlock(balance2, balance1, 500_000L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();
  }

  @Test
  void transfer() throws InterruptedException {

    Balance balance1 = new Balance(1_000_000L);
    Balance balance2 = new Balance(1_000_000L);

    Thread thread1 = new Thread(() -> {
      try {
        Balance.transfer(balance1, balance2, 300_000L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    Thread thread2 = new Thread(() -> {
      try {
        Balance.transfer(balance2, balance1, 500_000L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    System.out.println(balance1.getValue());
    System.out.println(balance2.getValue());
  }
}
