package com.m19y.learn.legacy;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class DateTest {

  private static final Logger log = LoggerFactory.getLogger(DateTest.class);
  @Test
  void legacyDateTest() {

    Date date = new Date(999360000000L); // using milliseconds
    Date currentDate = new Date();
    Date systemDate = new Date(System.currentTimeMillis());

    log.info(date.toString());
    log.info(currentDate.toString());
    log.info(systemDate.toString());

  }
}
