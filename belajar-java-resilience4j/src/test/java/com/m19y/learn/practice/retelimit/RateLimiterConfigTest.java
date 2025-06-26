package com.m19y.learn.practice.retelimit;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class RateLimiterConfigTest {

  private final AtomicLong counter = new AtomicLong(0L);
  @Test
  void testRateLimitConfig() {

    /*
    * Pada config ini kita akan mengkesekusi kode sebanyak 100 kali per-eksekusi
    * dan setiap ekeskusi ke 100 maka akan dilakukan refresh (mengulang dari 1 - 100)
    * pada saat refresh kita membutuhkan 1 menit setiap refresh.
    * Timeout durasinya akan di set 2 detik,
    * Ini akan menyebabkan error RequestNotPermitted karena akan kode kita akan time out sebelum di refresh
    */
    RateLimiterConfig config = RateLimiterConfig.custom()
            .limitForPeriod(100) // hanya mengeksekusi 100 per periode
            .limitRefreshPeriod(Duration.ofMinutes(1)) // akan di refresh setiap 1 minute dan 100 nya akan direset menjadi 0
            .timeoutDuration(Duration.ofSeconds(2)) // akan menunggu selama 2 detik sebelum time out
            .build();

    RateLimiter rateLimiter = RateLimiter.of("bro", config);
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
