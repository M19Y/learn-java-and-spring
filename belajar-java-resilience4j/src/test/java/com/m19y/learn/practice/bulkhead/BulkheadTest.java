package com.m19y.learn.practice.bulkhead;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

@Slf4j
public class BulkheadTest {

  private final AtomicLong atomicLong = new AtomicLong(0L);

  @SneakyThrows
  void slow(){
    long value = atomicLong.incrementAndGet();
    log.info("Slow {}", value);
    Thread.sleep(3_000L);
  }

  @Test
  void testSemaphore() throws InterruptedException {

    // bulkhead max concurrent call is 25
    Bulkhead bulkhead = Bulkhead.ofDefaults("unc");

    for (int i = 0; i < 1000; i++) {
      Runnable runnable = Bulkhead.decorateRunnable(bulkhead, this::slow);
      new Thread(runnable).start();
    }

    Thread.sleep(10_000L);
  }

  @Test
  void testThreadPool() {

    log.info(String.valueOf(Runtime.getRuntime().availableProcessors())); // my processors are 12

    ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead.ofDefaults("aunty");

    Assertions.assertThrows(BulkheadFullException.class, () -> {
      for (int i = 0; i < 1000; i++) {
        Supplier<CompletionStage<Void>> supplier =
                ThreadPoolBulkhead.decorateRunnable(threadPoolBulkhead, this::slow);
        supplier.get();
      }
    });
  }
}

