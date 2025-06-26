package com.m19y.learn.practice.retry;

import io.github.resilience4j.retry.Retry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

@Slf4j
public class RetryTest {

  void callMe(){
    log.info("Try call me");
    throw new IllegalArgumentException("Ups Error");
  }

  @Test
  void withoutRetry() {
    // callMe(); // sekali dipanggil akan langsung error (lihat pada log-nya)
    IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, this::callMe);
    Assertions.assertEquals("Ups Error", error.getMessage());
  }

  @Test
  void withRetry() {
    Retry retry = Retry.ofDefaults("Bro");

    // walaupun sama sama akan melemparkan error
    // tapi kode dengan retry ini akan mencoba
    // memanggil kembali kode callMe() selama 3 kali
    IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Runnable runnable = Retry.decorateRunnable(retry, this::callMe);
      runnable.run();
    });
    Assertions.assertEquals("Ups Error", error.getMessage());
  }

  String hello(){
    log.info("Call hello method");
    throw new IllegalArgumentException("Something wrong with this hello method");
  }
  @Test
  void retrySupplier() {
    // retry with return value

    // akan di coba sebayak 3 kali oleh retry
    Retry retry = Retry.ofDefaults("Sis");
    IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Supplier<String> stringSupplier = Retry.decorateSupplier(retry, this::hello);
      stringSupplier.get();
    });
    Assertions.assertEquals("Something wrong with this hello method", error.getMessage());
  }
}
