package com.m19y.learn;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CircuitBrakerConfigTest {

  void CallMe(){
    log.info("invoke callMe()");
    throw new IllegalArgumentException("Something went wrong");
  }

  @Test
  void testCircuitBrakerConfig() {

    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .failureRateThreshold(10f) // jika di atas 10% maka state nya ubah ke OPEN
            .slidingWindowSize(10)
            .minimumNumberOfCalls(10)
            .build();

    CircuitBreaker circuitBreaker = CircuitBreaker.of("grandma", config);

    for (int i = 0; i < 200; i++) {

      try{
        Runnable runnable = CircuitBreaker.decorateRunnable(circuitBreaker, this::CallMe);
        runnable.run();
      }catch (Exception e){
        log.info("Error -> {}", e.getMessage());
      }
    }

  }
}
