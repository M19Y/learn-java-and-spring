package com.m19y.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class LoggingTest {

  @Test
  void testLongLogging() {

    for (int i = 0; i < 100_000; i++) {
      log.warn("Hello {}",  i);
    }
  }

  @Test
  void testLogging() {

    log.info("Ini pesan info");
    log.warn("Ini pesan warning");
    log.error("Ini pesan error");
  }
}
