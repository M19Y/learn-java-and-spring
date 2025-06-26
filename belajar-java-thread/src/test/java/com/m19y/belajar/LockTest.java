package com.m19y.belajar;


import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;


public class LockTest {


  @Test
  void testLock() throws InterruptedException {
    CounterLock counter = new CounterLock();

    Runnable runnable = () -> {
      for (int i = 0; i < 1_000_000; i++) {
        counter.increment();
      }
    };

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


  @Test
  void testReadWriteLock() throws InterruptedException {
    CounterReadWriteLock counter = new CounterReadWriteLock();

    Runnable runnable = () -> {
      for (int i = 0; i < 1_000_000; i++) {
        counter.increment();
      }
    };

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

  String message = null;

  // cara ini sama dengan notify and notifyAll namun lebih moderen
  @Test
  void conditionTest() throws InterruptedException {
    final Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    Thread thread1 = new Thread(() -> {
      try{
        lock.lock();
        condition.await();
        System.out.println(message);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        lock.unlock();
      }
    });

    Thread thread2 = new Thread(() -> {
      try{
        lock.lock();
        Thread.sleep(2000);
        message = "Condition log message";
        condition.signal();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        lock.unlock();
      }
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();
  }
}
