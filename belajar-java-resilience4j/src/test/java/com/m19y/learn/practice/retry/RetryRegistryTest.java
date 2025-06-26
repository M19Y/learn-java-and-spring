package com.m19y.learn.practice.retry;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@Slf4j
public class RetryRegistryTest {


  @Test
  void testSingleTonRetryRegistry() {

    // Retry registry menganut konsep single ton
    RetryRegistry retryRegistry = RetryRegistry.ofDefaults();
    Retry retry1 = retryRegistry.retry("bro"); // object di buat baru
    Retry retry2 = retryRegistry.retry("bro"); // object di refrence ke retry yang memiliki nama yang sama "bro"

    Assertions.assertSame(retry1, retry2);
  }

  @Test
  void testRetryRegistryWithConfig() {

    RetryConfig config = RetryConfig.custom()
            .waitDuration(Duration.ofSeconds(2))
            .maxAttempts(10)
            .build();

    // Menambahkan config pada retryRegistry
    // cara 1
    // RetryRegistry retryRegistry = RetryRegistry.of(config);

    // cara 2
    RetryRegistry retryRegistry = RetryRegistry.ofDefaults();
    retryRegistry.addConfiguration("config", config);

    Retry retry1 = retryRegistry.retry("bro"); // tidak memiliki config (default)
    Retry retry2 = retryRegistry.retry("bro", "config"); // tidak memiliki config, karena retry 1 tidak mendefinisikan confignya
    Retry retry3 = retryRegistry.retry("sis", "config");// memiliki config custom
    // Assertions.assertThrows(IllegalArgumentException.class,
    //         () -> retry1.executeRunnable(this::callMe)); // hanya 3 kali di panggil

    // retry dilakukan 3 kali
    Assertions.assertThrows(IllegalArgumentException.class,
            () -> Retry.decorateRunnable(retry2, this::callMe).run());

    System.out.println("==============\n");

    // retry dilakukan 10 kali selama 2 detik
    Assertions.assertThrows(IllegalArgumentException.class,
            () -> Retry.decorateRunnable(retry3, this::callMe).run());
  }

  void callMe(){
    log.info("invoke callMe()");
    throw new IllegalArgumentException("Something went wrong when invoke callMe()");
  }
}
