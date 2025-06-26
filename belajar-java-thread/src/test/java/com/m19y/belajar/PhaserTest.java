package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

  // pasher sebagai countDownLatch
  @Test
  void phaserAsCountDownLatch() throws InterruptedException {

    final Phaser phaser = new Phaser();
    final ExecutorService executor = Executors.newFixedThreadPool(10);

    executor.execute(() -> {
      phaser.awaitAdvance(0);
      System.out.println("Finish all task");
    });

    phaser.bulkRegister(5);
    for (int i = 0; i < 5; i++) {
      executor.execute(() -> {
        // phaser.register(); // satu satu
        try {
          System.out.println("Start task");
          Thread.sleep(2000);
          System.out.println("Finish task");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally {
          phaser.arrive();
        }
      });
    }

    executor.awaitTermination(10, TimeUnit.SECONDS);

  }

  // phaser sebagai cyclicBarrier

  @Test
  void phaserAsCyclicBarrier() throws InterruptedException {
    final Phaser phaser = new Phaser();
    final ExecutorService executor = Executors.newFixedThreadPool(10);

    phaser.bulkRegister(5);

    for (int i = 0; i < 4; i++) {
      executor.execute(() -> {
        System.out.println("Wait");
        phaser.arriveAndAwaitAdvance();
        System.out.println("Finish");
      });
    }

    executor.awaitTermination(5, TimeUnit.SECONDS);

  }
}
