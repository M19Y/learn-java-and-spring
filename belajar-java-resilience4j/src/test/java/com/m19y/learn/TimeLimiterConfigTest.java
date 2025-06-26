package com.m19y.learn;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.*;

@Slf4j
public class TimeLimiterConfigTest {


  @SneakyThrows
  String slow(){
    log.info("start slow() method");
    Thread.sleep(5_000L);
    log.info("finish slow() method");
    return "Bro";
  }


  @Test
  void testTimerLimitConfig() throws Exception {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<String> submitSlow = executorService.submit(this::slow);

    TimeLimiterConfig config = TimeLimiterConfig.custom()
            .timeoutDuration(Duration.ofSeconds(7))
            .cancelRunningFuture(true)
            .build();

    TimeLimiter limiter = TimeLimiter.of("sis", config);
    Callable<String> stringCallable = TimeLimiter.decorateFutureSupplier(limiter, () -> submitSlow);

    Assertions.assertDoesNotThrow(stringCallable::call);
    Assertions.assertEquals("Bro", stringCallable.call());

  }
}
