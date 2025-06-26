package com.m19y.learn;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class RetryRegistryTest {


  private static final Logger log = LoggerFactory.getLogger(RetryRegistryTest.class);

  void callMe(){
    log.info("call me pls");
    throw new RuntimeException("Ups error bro");
  }

  @Test
  void testRetryRegistry() {

    RetryRegistry registry = RetryRegistry.ofDefaults();

    Retry simple1 = registry.retry("simple");
    Retry simple2 = registry.retry("simple");

    Assertions.assertSame(simple1, simple2);
  }

  @Test
  void testRetryRegistryConfig() {

    RetryConfig config = RetryConfig.custom()
        .maxAttempts(5)
        .ignoreExceptions(IllegalArgumentException.class)
        .waitDuration(Duration.ofSeconds(2))
        .build();

    RetryRegistry registry = RetryRegistry.ofDefaults();

    // add config to registry
    registry.addConfiguration("my-config", config);

    Retry simple1 = registry.retry("simple", "my-config");
    Retry simple2 = registry.retry("simple");

    Assertions.assertSame(simple1, simple2);

    Runnable runnable = Retry.decorateRunnable(simple1, () -> callMe());
    runnable.run();

  }
}
