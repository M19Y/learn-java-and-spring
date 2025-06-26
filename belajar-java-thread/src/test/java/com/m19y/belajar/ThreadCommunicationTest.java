package com.m19y.belajar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

  private String message;

  @BeforeEach
  void setUp() {
    message = null;
  }

  @Test
  void manual() throws InterruptedException {
    Thread thread1 = new Thread(() -> {
      System.out.println("first thread");
      while(message == null){
        // wait until the message is not null
        System.out.println("wait");
      }

      System.out.println(message);
    });

    Thread thread2 = new Thread(() -> {
      System.out.println("second thread");
      message = "Manual Thread communication";
    });

    thread1.start();
    thread2.start();

    thread2.join();
    thread1.join();
  }

  @Test
  void waitAndNotify() throws InterruptedException {

    // apapun Object di java bisa dijadikan lock
    final Object lock = new Object();

    Thread thread1 = new Thread(() ->{
      System.out.println("First thread");
      synchronized (lock){
        System.out.println("wait a notify");
        try {
          lock.wait();
          System.out.println(message);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread thread2 = new Thread(() ->{
      System.out.println("Second thread");
      synchronized (lock){
        message = "Wait and notify communication completed";
        lock.notify();
      }
    });

    // order sangat berpengaruh;

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

  }

  @Test
  void waitAndNotifyAll() throws InterruptedException {

    final Object lock = new Object();

    Thread thread1 = new Thread(() ->{
      System.out.println("First thread");
      synchronized (lock){
        System.out.println("wait a notify");
        try {
          lock.wait();
          System.out.println(message + " (1)");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread thread3 = new Thread(() ->{
      System.out.println("Third thread");
      synchronized (lock){
        System.out.println("wait a notify");
        try {
          lock.wait();
          System.out.println(message + " (3)");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread thread2 = new Thread(() ->{
      System.out.println("Second thread");
      synchronized (lock){
        message = "Wait and notify communication completed";
        lock.notifyAll();
      }
    });

    // order sangat berpengaruh;

    thread1.start();
    thread3.start();
    thread2.start();

    thread1.join();
    thread3.join();
    thread2.join();

  }

}
