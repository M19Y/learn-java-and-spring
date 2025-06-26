package com.m19y.learn;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CircuitBrakerRegistryTest {

  void CallMe(){
    log.info("invoke callMe()");
    throw new IllegalArgumentException("Something went wrong");
  }

  @Test
  void testCircuitBrakerConfigWithRegistry() {

    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .failureRateThreshold(10f) // jika di atas 10% maka state nya ubah ke OPEN
            .slidingWindowSize(10)
            .minimumNumberOfCalls(10)
            .build();

    // registry
    CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();
    registry.addConfiguration("config", config);

    CircuitBreaker circuitBreaker = registry.circuitBreaker("grandma", "config");

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
