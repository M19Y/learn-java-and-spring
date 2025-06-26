package com.m19y.learn;

import io.github.resilience4j.retry.Retry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

@Slf4j
public class EventPublisherTest {

  // Tiap tiap module di resilience4j memiliki event publisher
  // masing masing module memiliki method yang berbeda beda untuk mendaptkan event publisher-nya
  @Test
  void eventPublisherForRetryTest() {

    Retry retry = Retry.ofDefaults("bro");
    retry.getEventPublisher().onRetry(event -> log.info("try to retry"));

    try {
      Supplier<String> stringSupplier = Retry.decorateSupplier(retry, this::hello);
      System.out.println(stringSupplier.get());
    } catch (Exception e) {
      System.out.println(retry.getMetrics().getNumberOfFailedCallsWithoutRetryAttempt());
      System.out.println(retry.getMetrics().getNumberOfFailedCallsWithRetryAttempt());
      System.out.println(retry.getMetrics().getNumberOfSuccessfulCallsWithoutRetryAttempt());
      System.out.println(retry.getMetrics().getNumberOfTotalCalls());
      System.out.println(retry.getMetrics().getNumberOfSuccessfulCallsWithRetryAttempt());
    }
  }

  String hello() {
    throw new IllegalArgumentException("Ups");
  }
}
