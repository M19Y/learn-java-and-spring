package com.m19y.learn;

import java.time.Duration;

public class PlatformThreadApp {

  public static void main(String[] args) {

    for (int i = 0; i < 10; i++) {
      Thread unstarted = Thread.ofPlatform().name("pt-" + i).daemon(false).unstarted(() -> {
        try {
          Thread.sleep(Duration.ofSeconds(2));
          System.out.println("Hello " + Thread.currentThread() + " !");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
      unstarted.start();
    }
  }
}
