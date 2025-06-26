package com.m19y.learn;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CircuitBrakerTest {

  void CallMe(){
    log.info("invoke callMe()");
    throw new IllegalArgumentException("Something went wrong");
  }

  @Test
  void testDefaultCircuitBraker() {

    // default error rate-nya adalah 100
    // dan apabila error nya diatas 50% maka statenya akan menjadi OPEN (melakukan reject semua call)
    CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("grandma");

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
