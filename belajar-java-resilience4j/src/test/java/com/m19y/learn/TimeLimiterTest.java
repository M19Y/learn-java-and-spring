package com.m19y.learn;

import io.github.resilience4j.timelimiter.TimeLimiter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

@Slf4j
public class TimeLimiterTest {


  @SneakyThrows
  String slow(){
    log.info("start slow() method");
    Thread.sleep(5_000L);
    log.info("finish slow() method");
    return "Bro";
  }


  @Test
  void testDefaultTimerLimit() throws Exception {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<String> submitSlow = executorService.submit(this::slow);

    TimeLimiter limiter = TimeLimiter.ofDefaults("sis");
    Callable<String> stringCallable = TimeLimiter.decorateFutureSupplier(limiter, () -> submitSlow);

    Assertions.assertThrows(TimeoutException.class, stringCallable::call);

  }
}
