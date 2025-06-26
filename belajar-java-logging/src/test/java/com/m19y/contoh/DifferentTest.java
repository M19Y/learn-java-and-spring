package com.m19y.contoh;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DifferentTest {

  private static final Logger log = LoggerFactory.getLogger(DifferentTest.class);

  @Test
  void levelTest(){
    log.trace("simple trace log from different test");
    log.debug("simple debug log from different test");
    log.info("simple info log from different test");
    log.warn("simple warn log from different test");
    log.error("simple error log from different test");
  }
}
