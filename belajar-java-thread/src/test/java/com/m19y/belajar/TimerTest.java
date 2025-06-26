package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

  @Test
  void delayedJob() throws InterruptedException {

    // buat task yang akan dieksekusi nanti
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        System.out.println("Do something fun");
      }
    };

    // tentukan kapan task akan dieksekusi
    Timer timer = new Timer();
    timer.schedule(task, 2000L);

    Thread.sleep(3000L);

  }

  @Test
  void periodicJob() throws InterruptedException {

    // buat task yang akan dieksekusi nanti
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        System.out.println("Do something fun");
      }
    };

    // tentukan kapan task akan dieksekusi
    Timer timer = new Timer();
    timer.schedule(task, 2000L, 2000L);

    // tunggu selama 10 dekit, dan tasknya harusnya keluar 5x
    Thread.sleep(11_000L);

  }
}
