package com.m19y.learn.practice.retry;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

@Slf4j
public class RetryConfigTest {

  String hello(){
    log.info("call hello()");
    throw new IllegalArgumentException("Something went wrong on hello()");
  }
  @Test
  void testConfig() {

    RetryConfig config = RetryConfig.custom()
            .maxAttempts(5) // berapa kali mau di retry?
            .waitDuration(Duration.ofSeconds(2)) // berapa lama waktu di retry setelah gagal?
            .retryExceptions() // excpetion apa saja yang mau diretry
            .ignoreExceptions() // exception apa saja yang mau di ignore
            .build();

    Retry retry = Retry.of("bro", config);

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Supplier<String> stringSupplier = Retry.decorateSupplier(retry, this::hello);
      stringSupplier.get();
    });
  }
}
