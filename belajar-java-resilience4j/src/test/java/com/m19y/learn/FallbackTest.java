package com.m19y.learn;

import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

@Slf4j
public class FallbackTest {

  @SneakyThrows
  String sayHello() {
    log.info("invoke sayHello()");
    Thread.sleep(2_000L);
    throw new IllegalArgumentException("Something went wrong");
  }

  @Test
  void testDecoratorsWithFallback() throws InterruptedException {

    RateLimiter rateLimiter = RateLimiter.of("bro-rate-limiter", RateLimiterConfig.custom()
            .limitForPeriod(5)
            .limitRefreshPeriod(Duration.ofSeconds(1))
            .build());

    Retry retry = Retry.of("bro-retry", RetryConfig.custom()
            .maxAttempts(10)
            .waitDuration(Duration.ofMillis(10))
            .build());

    Supplier<String> supplier = Decorators.ofSupplier(this::sayHello)
            .withRetry(retry)
            .withRateLimiter(rateLimiter)
            .withFallback(throwable -> "Bro")
            .decorate();

    System.out.println(supplier.get());
  }
}
