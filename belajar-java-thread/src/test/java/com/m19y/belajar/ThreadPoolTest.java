package com.m19y.belajar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

  private int minThread;
  private int maxThread;
  private int alive;
  private TimeUnit aliveTime;

  @BeforeEach
  void setUp() {
    minThread = 10;
    maxThread = 100;
    alive = 1;
    aliveTime = TimeUnit.MINUTES;
  }

  @Test
  void create() {
    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

  }

  @Test
  void executeRunnable() throws InterruptedException {
    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
    ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

    executor.execute(() -> {
      try {
        Thread.sleep(2000L);
        System.out.println("Runnable from thread: " + Thread.currentThread().getName());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    Thread.sleep(3000L);
  }

  @Test
  void shutdownThread() throws InterruptedException {
    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);
    ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

    for (int i = 0; i < 1000; i++) {
      final int task = i;

      executor.execute(() -> {
        try {
          Thread.sleep(1000L);
          System.out.println("Task " + task +  " from thread: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    executor.shutdown();
    executor.awaitTermination(1L, TimeUnit.DAYS);
  }
  
  private static class LogRejectedExecutionHandler implements RejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      System.out.println("Task " + r + " is rejected!");
    }
  }

  @Test
  void rejected() throws InterruptedException {
    ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
    var rejectedHandler = new LogRejectedExecutionHandler();
    ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue, rejectedHandler);

    for (int i = 0; i < 1000; i++) {
      final int task = i;

      executor.execute(() -> {
        try {
          Thread.sleep(1000L);
          System.out.println("Task " + task +  " from thread: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    executor.shutdown();
    executor.awaitTermination(1L, TimeUnit.DAYS);
  }
}
