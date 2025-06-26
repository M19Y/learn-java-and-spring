package com.m19y.learn;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class BulkheadTest {


  private static final Logger log = LoggerFactory.getLogger(BulkheadTest.class);
  private final AtomicLong counter = new AtomicLong(0L);

  void slow() {
    try {
    log.info("Slow - {}", counter.incrementAndGet());
      Thread.sleep(5_000L);
    } catch (InterruptedException e) {
      log.info(e.getMessage());
    }
  }

  @Test
  void testBulkheadSemaphore() throws InterruptedException {

    Bulkhead bulkhead = Bulkhead.ofDefaults("simple-bulkhead");

    for (int i = 0; i < 1_000; i++) {
      Runnable runnable = Bulkhead.decorateRunnable(bulkhead, this::slow);
      new Thread(runnable).start();

    }
    Thread.sleep(10_000L);
  }

  @Test
  void testBulkheadThreadPool() {

    log.info(String.valueOf(Runtime.getRuntime().availableProcessors()));
    ThreadPoolBulkhead bulkhead = ThreadPoolBulkhead.ofDefaults("simple");
    for (int i = 0; i < 1_000; i++) {
      Supplier<CompletionStage<Void>> supplier = ThreadPoolBulkhead.decorateRunnable(bulkhead, this::slow);
      supplier.get();
    }

  }
}
