package com.m19y.belajar;

import org.junit.jupiter.api.Test;

public class ThreadTest {

  @Test
  void mainThread() {

    // Mengambil thread saat ini
    System.out.println(Thread.currentThread().getName());

  }

  @Test
  void createThread() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello from thread: " + Thread.currentThread().getName());
      }
    };

    // main thread
    runnable.run();

    Thread thread = new Thread(runnable);

    // dieksekusi secara asynchronous
    thread.start();

    System.out.println("Program selesai");
  }

  @Test
  void threadSleep(){
    Runnable runnable = () -> {
      try{
        Thread.sleep(2_000L);
        System.out.println("Hello from thread: " + Thread.currentThread().getName());
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    };

    Thread thread = new Thread(runnable);
    thread.start();

    System.out.println("Program selesai");

    // program akan langsung di akhiri apabila kita tidak menunggu response dari thread runnablenya
    // oleh karena itu, untuk mendapatkan output dari runnable diatas maka kita harus membuat thread sleep lagi

    try {
      Thread.sleep(4_000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void threadJoin(){
    Runnable runnable = () -> {
      try{
        Thread.sleep(2_000L);
        System.out.println("Hello from thread: " + Thread.currentThread().getName());
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    };

    Thread thread = new Thread(runnable);

    thread.start();
    System.out.println("Menunggu program");
    try {
      thread.join();
    }catch (InterruptedException e){
      e.printStackTrace();
    }
    System.out.println("Program selesai");

  }

  @Test
  void wrongThreadInterrupt() throws InterruptedException {
    Runnable runnable = () -> {
      for (int i = 1; i <= 10; i++) {
        try{
          Thread.sleep(1_000L);
          System.out.println("runnable index: " + i);
        }catch (InterruptedException e){
          e.printStackTrace();
        }
      }
    };

    Thread thread = new Thread(runnable);

    thread.start();
    System.out.println("Menunggu program");
    Thread.sleep(5_000L);
    thread.interrupt();
    thread.join();
    System.out.println("Program selesai");
  }

  @Test
  void rightThreadInterrupt() throws InterruptedException {
    Runnable runnable = () -> {

      for (int i = 1; i <= 10; i++) {
        // manual check interrupted
        if (Thread.interrupted()){
          return;
        }
        try {
          Thread.sleep(1_000L);
          System.out.println("runnable index: " + i);
        } catch (InterruptedException e) {
          return;
        }
      }

    };

    Thread thread = new Thread(runnable);

    thread.start();

    System.out.println("Menunggu program");
    Thread.sleep(5_000L);
    thread.interrupt();

    thread.join();
    System.out.println("Program selesai");

  }

  @Test
  void threadName() {

    Thread thread = new Thread(() -> {
      System.out.println("run in thread: " + Thread.currentThread().getName());
    });

    thread.setName("gomu gomu no");

    thread.start();
  }

  @Test
  void threadState() throws InterruptedException {

    Thread thread = new Thread(() -> {
      System.out.println(Thread.currentThread().getState() + ":inside");
      System.out.println("run in thread: " + Thread.currentThread().getName());
    });


    System.out.println(thread.getState()); // NEW
    thread.setName("gomu gomu no");
    thread.start(); // RUNNABLE:inside
    thread.join();

    // setelah selesai
    System.out.println(thread.getState()); // TERMINATED
  }
}
