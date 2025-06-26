package com.m19y.practice;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

  @Test
  void delayedJob() throws InterruptedException {

    TimerTask delayedJob = new TimerTask() {
      @Override
      public void run() {
        System.out.println("Delayed Job");
      }
    };

    Timer timer = new Timer();
    timer.schedule(delayedJob, 1000);

    // junit tidak akan menunggu schedule sampai selesai. kita harus menggunakan sleep
    Thread.sleep(3000);
  }

  @Test
  void periodicJob() throws InterruptedException {

    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        System.out.println("Holla");
      }
    };

    Timer timer = new Timer();
    timer.schedule(task, 2000, 2000);

    Thread.sleep(10_000);
  }
}
