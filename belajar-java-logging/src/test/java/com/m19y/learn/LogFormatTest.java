package com.m19y.learn;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFormatTest {

  private static final Logger log = LoggerFactory.getLogger(LogFormatTest.class);

  @Test
  void logFormatTest() {
    log.info("without parameter");

    int aNum1 = 10;
    int aNum2 = 10;
    int result = aNum1 * aNum2;

    log.info("{} * {} = {}", aNum1, aNum2, result);

    log.error("Ups", new NullPointerException());
  }
}
