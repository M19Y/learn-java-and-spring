package com.m19y.learn;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LevelTest {

  private static final Logger log = LoggerFactory.getLogger(LevelTest.class);

  @Test
  void levelTest(){
    log.trace("simple trace log");
    log.debug("simple debug log");
    log.info("simple info log");
    log.warn("simple warn log");
    log.error("simple error log");
  }
}
