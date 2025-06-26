package com.m19y.learn;

import io.github.resilience4j.ratelimiter.RateLimiter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class RateLimiterTest {

  private static final Logger log = LoggerFactory.getLogger(RateLimiterTest.class);

  private final AtomicLong counter = new AtomicLong(0L);

  @Test
  void testRateLimiter() {

    RateLimiter limiter = RateLimiter.ofDefaults("simple-limiter");

    for (int i = 0; i < 10_000; i++) {
      Runnable runnable = RateLimiter.decorateRunnable(limiter, () -> {
        long result = counter.incrementAndGet();
        log.info(" current {}", result);
      });

      runnable.run();
    }
  }
}
