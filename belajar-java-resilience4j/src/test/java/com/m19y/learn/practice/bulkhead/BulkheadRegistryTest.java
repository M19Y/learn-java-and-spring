package com.m19y.learn.practice.bulkhead;

import io.github.resilience4j.bulkhead.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

@Slf4j
public class BulkheadRegistryTest {


  private final AtomicLong atomicLong = new AtomicLong(0L);

  @SneakyThrows
  void slow(){
    long value = atomicLong.incrementAndGet();
    log.info("Slow {}", value);
    Thread.sleep(3_000L);
  }

  @Test
  void testSemaphoreWithRegistry() throws InterruptedException {

    BulkheadConfig config = BulkheadConfig.custom()
            .maxConcurrentCalls(5)
            .maxWaitDuration(Duration.ofSeconds(5))
            .build();

    BulkheadRegistry registry = BulkheadRegistry.ofDefaults();
    registry.addConfiguration("config", config);

    Bulkhead bulkhead = registry.bulkhead("unc", "config");

    for (int i = 0; i < 1000; i++) {
      Runnable runnable = Bulkhead.decorateRunnable(bulkhead, this::slow);
      new Thread(runnable).start();
    }

    Thread.sleep(10_000L);
  }

  @Test
  void testThreadPoolWithConfig() throws InterruptedException {

    log.info(String.valueOf(Runtime.getRuntime().availableProcessors())); // my processors are 12

    ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
            .maxThreadPoolSize(5)
            .coreThreadPoolSize(5)
            .build();

    ThreadPoolBulkheadRegistry registry = ThreadPoolBulkheadRegistry.ofDefaults();
    registry.addConfiguration("config", config);

    ThreadPoolBulkhead threadPoolBulkhead = registry.bulkhead("aunty", "config");

    for (int i = 0; i < 20; i++) {
      Supplier<CompletionStage<Void>> supplier =
              ThreadPoolBulkhead.decorateRunnable(threadPoolBulkhead, this::slow);
      supplier.get();
    }

    Thread.sleep(10_000L);
  }


}
