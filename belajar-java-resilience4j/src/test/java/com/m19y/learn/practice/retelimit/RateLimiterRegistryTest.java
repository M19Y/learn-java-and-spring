package com.m19y.learn.practice.retelimit;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class RateLimiterRegistryTest {

  private final AtomicLong counter = new AtomicLong(0L);

  @Test
  void testRateLimitRegistryWithConfig() {

    RateLimiterConfig config = RateLimiterConfig.custom()
            .limitForPeriod(100)
            .limitRefreshPeriod(Duration.ofMinutes(1))
            .timeoutDuration(Duration.ofSeconds(2))
            .build();

    // disarankan untuk menggunkan registry untuk membuat ratelimiter
    RateLimiterRegistry registry = RateLimiterRegistry.ofDefaults();
    registry.addConfiguration("config", config);

    // struktor kode penggunaan ratelimiter registry sama seperti penggunaan registry pada retry dan
    RateLimiter rateLimiter = registry.rateLimiter("bro", "config");


    Assertions.assertThrows(RequestNotPermitted.class, () -> {
      for (int i = 0; i < 10_000; i++) {
        Runnable runnable = RateLimiter.decorateRunnable(rateLimiter, () -> {
          long result = counter.incrementAndGet();
          log.info("Result: {}", result);
        });

        runnable.run();
      }
    });

  }
}
