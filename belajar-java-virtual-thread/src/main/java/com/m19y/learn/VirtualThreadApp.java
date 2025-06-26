package com.m19y.learn;

import java.io.IOException;
import java.time.Duration;

public class VirtualThreadApp {

  public static void main(String[] args) throws IOException {

    for (int i = 0; i < 10_000; i++) {
      Thread unstarted = Thread.ofVirtual().name("pt-" + i).unstarted(() -> {
        try {
          Thread.sleep(Duration.ofSeconds(2));
          System.out.println("Hello " + Thread.currentThread() + " !");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
      unstarted.start();
    }

    System.in.read();
  }
}
