package com.m19y.learn;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.Supplier;

public class RetryConfigTest {

  private static final Logger log = LoggerFactory.getLogger(RetryConfigTest.class);

  void callMe(){
    log.info("call me pls");
    throw new RuntimeException("Ups error bro");
  }
  @Test
  void configTest() {

    RetryConfig config = RetryConfig.custom()
        .maxAttempts(5)
        .waitDuration(Duration.ofSeconds(2))
//        .retryExceptions(IllegalArgumentException.class)
        .ignoreExceptions(IllegalArgumentException.class)
        .build();

    Retry retry = Retry.of("simple-config", config);
    Runnable runnable = Retry.decorateRunnable(retry, () -> callMe());
    runnable.run();
  }
}
