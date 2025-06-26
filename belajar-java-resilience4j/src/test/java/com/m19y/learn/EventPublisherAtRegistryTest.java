package com.m19y.learn;

import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class EventPublisherAtRegistryTest {

  @Test
  void testRegistry() {

    RetryRegistry registry = RetryRegistry.ofDefaults();
    registry.getEventPublisher().onEntryAdded(event -> {
      log.info("new retry added -> {}", event.getAddedEntry().getName());
    });
    
    registry.retry("bro");
    registry.retry("bro");
    registry.retry("nande");

    // harusnya eventnya hanya 2 saja yaitu bro, dan nande
  }
}
