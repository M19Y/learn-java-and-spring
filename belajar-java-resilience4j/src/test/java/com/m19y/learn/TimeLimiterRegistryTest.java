package com.m19y.learn;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class TimeLimiterRegistryTest {


  @SneakyThrows
  String slow(){
    log.info("start slow() method");
    Thread.sleep(5_000L);
    log.info("finish slow() method");
    return "Bro";
  }


  @Test
  void testTimerLimitRegistry() throws Exception {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<String> submitSlow = executorService.submit(this::slow);

    TimeLimiterConfig config = TimeLimiterConfig.custom()
            .timeoutDuration(Duration.ofSeconds(7))
            .cancelRunningFuture(true)
            .build();

    TimeLimiterRegistry registry = TimeLimiterRegistry.ofDefaults();
    registry.addConfiguration("config", config);

    TimeLimiter limiter = registry.timeLimiter("sis", "config");
    Callable<String> stringCallable = TimeLimiter.decorateFutureSupplier(limiter, () -> submitSlow);

    Assertions.assertDoesNotThrow(stringCallable::call);
    Assertions.assertEquals("Bro", stringCallable.call());

  }
}
