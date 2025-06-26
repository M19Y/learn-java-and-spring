package com.m19y.learn;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {


  private static final Logger log = LoggerFactory.getLogger(MainTest.class);

  @Test
  void simpleLogger(){
    System.out.println("this is a simple system out println");
    log.info("this is a simple log");
  }
}
