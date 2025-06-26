package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

  @Test
  void delayedJob() throws InterruptedException {

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

    ScheduledFuture<?> helloSchedule = executor.schedule(
            () -> System.out.println("Hello schedule"),
            5,
            TimeUnit.SECONDS);

    // get informasi lebih dibandingkan dengan timer
    long delay = helloSchedule.getDelay(TimeUnit.MILLISECONDS);

    System.out.println("Delay : " + delay);

    executor.awaitTermination(7, TimeUnit.SECONDS);
  }

  @Test
  void periodicJob() throws InterruptedException {

    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    executorService.scheduleAtFixedRate(
            () -> System.out.println("Periodic job"),
            2,
            3,
            TimeUnit.SECONDS);

    executorService.awaitTermination(10, TimeUnit.SECONDS);

  }
}
