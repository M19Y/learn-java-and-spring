package com.m19y.learn;

import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class DecoratorsTest {


  void CallMe() {
    log.info("invoke callMe()");
    throw new IllegalArgumentException("Something went wrong");
  }

  void slow() {
    try {
      log.info("Slow");
      Thread.sleep(1_000L);
    } catch (InterruptedException e) {
      log.info(e.getMessage());
    }
  }

  @Test
  void testDecorators() throws InterruptedException {

    RateLimiter rateLimiter = RateLimiter.of("bro-rate-limiter", RateLimiterConfig.custom()
            .limitForPeriod(5)
            .limitRefreshPeriod(Duration.ofSeconds(1))
            .build());

    Retry retry = Retry.of("bro-retry", RetryConfig.custom()
            .maxAttempts(10)
            .waitDuration(Duration.ofMillis(10))
            .build());

    Runnable runnable = Decorators.ofRunnable(this::slow)
            .withRetry(retry)
            .withRateLimiter(rateLimiter)
            .decorate();

    for (int i = 0; i < 100; i++) {
      new Thread(runnable).start();
    }

    Thread.sleep(10_000L);
  }
}
