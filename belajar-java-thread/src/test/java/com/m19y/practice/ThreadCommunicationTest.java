package com.m19y.practice;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

  private String message = null;

  @Test
  void endlessThreadCommunication() {
    Thread thread = new Thread(() -> {
      int count = 0;
      while(message == null) {
        System.out.println("wait-" + count);
        count++;
      }
      System.out.println(message);
    });

    thread.start();
  }

  @Test
  void manualThreadCommunication() throws InterruptedException {
    Thread thread = new Thread(() -> {
      while(message == null) {
        System.out.println("wait");
      }
      System.out.println(message);
    });

    Thread thread2 = new Thread(()-> {
      message = "Hallo";
    });

    thread.start();
    thread2.start();

    thread.join();
    thread2.join();
  }

  @Test
  void waitAndNotify() throws InterruptedException {

    final Object lock = new Object();
    Thread thread1 = new Thread(() -> {
      synchronized (lock){
        try {
          lock.wait();
          System.out.println(message);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    var thread2 = new Thread(() -> {
      synchronized (lock){
        message = "Wait and notify message";
        lock.notify();
      }
    });

    // urutan eksekusi kode sangat bepengaruh

    // wait tidak mendapt notify karena notify (thread2) jalan duluan
    /*
    thread2.start();
    thread1.start();

    thread2.join();
    thread1.join();
    */

    // wait (thread1) akan mendaptkan message karena wait jalan duluan
    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();




  }

  @Test
  void waitAndNotifyAll() throws InterruptedException {

    final Object lock = new Object();
    Thread thread1 = new Thread(() -> {
      synchronized (lock){
        try {
          lock.wait();
           System.out.println("From thread 1 : " + message);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    var thread2 = new Thread(() -> {
      synchronized (lock){
        try {
          lock.wait();
          System.out.println("From thread 2 : " + message);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    var thread3 = new Thread(() -> {
      synchronized (lock){
        message = "Wait and notify message";
        lock.notifyAll();
      }
    });

    // urutan eksekusi kode sangat bepengaruh
    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();

  }

}
