package com.m19y.learn;

import io.github.resilience4j.retry.Retry;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class RetryTest {

  private static final Logger log = LoggerFactory.getLogger(RetryTest.class);
  void callMe(){
    log.info("try call me");
    throw new IllegalArgumentException("Upps Error");
  }

  @Test
  void createRetryRunnable() {
    Retry retry = Retry.ofDefaults("jait");
    Runnable runnable = Retry.decorateRunnable(retry, () -> callMe());
    runnable.run();
  }

  String sayHallo(){
    log.info("call say hallo");
    throw new IllegalArgumentException("Ups error say hallo");
  }

  @Test
  void createRetrySupplier() {

    Retry retry = Retry.ofDefaults("terserah");
    Supplier<String> supplier = Retry.decorateSupplier(retry, () -> sayHallo());
    log.info(supplier.get());
  }
}
