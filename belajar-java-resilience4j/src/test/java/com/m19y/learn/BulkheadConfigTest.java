package com.m19y.learn;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class BulkheadConfigTest {


  private static final Logger log = LoggerFactory.getLogger(BulkheadConfigTest.class);
  private final AtomicLong counter = new AtomicLong(0L);

  void slow() {
    try {
    log.info("Slow - {}", counter.incrementAndGet());
      Thread.sleep(3_000L);
    } catch (InterruptedException e) {
      log.info(e.getMessage());
    }
  }

  @Test
  void testBulkheadSemaphoreConfig() throws InterruptedException {

    BulkheadConfig config = BulkheadConfig.custom()
        .maxConcurrentCalls(5)
        .maxWaitDuration(Duration.ofSeconds(5))
        .build();

    Bulkhead bulkhead = Bulkhead.of("simple-bulkhead", config);

    for (int i = 0; i < 10; i++) {
      Runnable runnable = Bulkhead.decorateRunnable(bulkhead, () -> slow());
      new Thread(runnable).start();

    }
    Thread.sleep(10_000L);
  }

  @Test
  void testBulkheadThreadPoolConfig() throws InterruptedException {

    ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
        .coreThreadPoolSize(5)
        .maxThreadPoolSize(5)
        .build();

    ThreadPoolBulkhead bulkhead = ThreadPoolBulkhead.of("simple", config);
    for (int i = 0; i < 20; i++) {
      Supplier<CompletionStage<Void>> supplier = ThreadPoolBulkhead.decorateRunnable(bulkhead, () -> slow());
      supplier.get();
    }

    Thread.sleep(10_000);
  }
}
