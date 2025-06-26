package com.m19y.learn.legacy;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

  private static final Logger log = LoggerFactory.getLogger(CalendarTest.class);

  @Test
  void testDateWithCalendar() {

    Calendar calendar = Calendar.getInstance();
    calendar.set(2001, Calendar.SEPTEMBER, 11);
    Date date = calendar.getTime();

    System.out.println(date);
    log.info(date.toString());

    Calendar simple = Calendar.getInstance();
    simple.set(Calendar.YEAR, 2001);
    simple.set(Calendar.MONTH, Calendar.SEPTEMBER);
    simple.set(Calendar.DATE, 2);
    simple.set(Calendar.HOUR, 4);
    simple.set(Calendar.MINUTE, 20);
    simple.set(Calendar.SECOND, 69);

    log.info("set calendar -> {}", simple.getTime());
    log.info("get calendar -> {} - {} - {}, {}",
        simple.get(Calendar.YEAR),
        simple.get(Calendar.MONTH),
        simple.get(Calendar.DATE),
        simple.get(Calendar.DAY_OF_WEEK)
    );

  }
}
