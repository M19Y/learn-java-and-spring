package com.m19y.practice;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

  @Test
  void create() {

    int minPool = 10;
    int maxPool = 100;
    long alive = 1;
    TimeUnit aliveTime = TimeUnit.MINUTES;
    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100); // 100 antrian

    ThreadPoolExecutor pool = new ThreadPoolExecutor(minPool, maxPool, alive, aliveTime, queue);

  }

  @Test
  void executeRunnable() throws InterruptedException {

    int minPool = 10;
    int maxPool = 100;
    long alive = 1;
    TimeUnit aliveTime = TimeUnit.MINUTES;
    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100); // 100 antrian

    ThreadPoolExecutor executor = new ThreadPoolExecutor(minPool, maxPool, alive, aliveTime, queue);
    Runnable runnable = () -> {
      try {
        Thread.sleep(3000);
        System.out.println("Running from thread -> " + Thread.currentThread().getName());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    };
    executor.execute(runnable);

    // kita harus menunggu selama 4 detik, karena junit tidak akan menunggu
    Thread.sleep(4000);
  }

  @Test
  void shutdown() throws InterruptedException {

    int minPool = 10;
    int maxPool = 100;
    long alive = 1;
    TimeUnit aliveTime = TimeUnit.MINUTES;
    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1_000); // 100 antrian

    ThreadPoolExecutor executor = new ThreadPoolExecutor(minPool, maxPool, alive, aliveTime, queue);
    for (int i = 0; i < 1_000; i++) {
      final int task = i;
      Runnable runnable = () -> {
        try {
          Thread.sleep(3000);
          System.out.println("Task" + task + " from thread -> " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      };
      executor.execute(runnable);
    }

//    executor.shutdown(); // langsung di matikan
    executor.awaitTermination(1, TimeUnit.DAYS); // menunggu hinga 1 hari
  }

  @Test
  void rejected() throws InterruptedException {

    int minPool = 10;
    int maxPool = 100;
    long alive = 1;
    TimeUnit aliveTime = TimeUnit.MINUTES;
    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
    LogRejectedExecutionHandler log = new LogRejectedExecutionHandler();

    ThreadPoolExecutor executor = new ThreadPoolExecutor(minPool, maxPool, alive, aliveTime, queue, log);
    for (int i = 0; i < 1_000; i++) {
      final int task = i;
      Runnable runnable = () -> {
        try {
          Thread.sleep(3000);
          System.out.println("Task" + task + " from thread -> " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      };
      executor.execute(runnable);
    }

    executor.awaitTermination(1, TimeUnit.DAYS); // menunggu hinga 1 hari
  }

  private static class LogRejectedExecutionHandler implements RejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      System.out.println("Task " + r + " is rejected");
    }
  }
}









