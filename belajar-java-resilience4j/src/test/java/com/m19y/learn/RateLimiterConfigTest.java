package com.m19y.learn;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

public class RateLimiterConfigTest {

  private static final Logger log = LoggerFactory.getLogger(RateLimiterConfigTest.class);

  private final AtomicLong counter = new AtomicLong(0L);

  @Test
  void testRateLimiter() {

    RateLimiterConfig config = RateLimiterConfig.custom()
        .limitForPeriod(100)
        .limitRefreshPeriod(Duration.ofMinutes(1))
        .timeoutDuration(Duration.ofSeconds(2))
        .build();

    RateLimiter limiter = RateLimiter.of("simple-limiter", config);

    for (int i = 0; i < 10_000; i++) {
      Runnable runnable = RateLimiter.decorateRunnable(limiter, () -> {
        long result = counter.incrementAndGet();
        log.info(" current {}", result);
      });

      runnable.run();
    }
  }
}
