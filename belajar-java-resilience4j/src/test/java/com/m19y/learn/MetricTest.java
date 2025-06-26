package com.m19y.learn;

import io.github.resilience4j.retry.Retry;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

public class MetricTest {

  // Tiap tiap module di resilience4j memiliki matric
  // dengan method yang berbeda beda

  // ini adalah contoh para retry module
  @Test
  void metricForRetryTest() {

    Retry retry = Retry.ofDefaults("bro");

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
