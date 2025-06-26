package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

public class LocalDateTest {

  private static final Logger log = LoggerFactory.getLogger(LocalDateTest.class);

  @Test
  void createSimpleLocalDate() {

    LocalDate dateNow = LocalDate.now();
    LocalDate dateOf = LocalDate.of(2001, Month.SEPTEMBER, 11);
    LocalDate dateParse = LocalDate.parse("2001-09-02"); // the format should be like this "yyyy-MM-dd"

    System.out.println(dateNow);
    System.out.println(dateOf);
    System.out.println(dateParse);
  }

  @Test
  void changeLocalDate() {

    LocalDate dateNow = LocalDate.now();
    LocalDate date1 = dateNow.withMonth(3);
    LocalDate date2 = dateNow.withYear(2001);

    Assertions.assertNotSame(date1, date2);
    // if today is maret it will be same, otherwise it will not
    Assertions.assertEquals(dateNow, date1);
    Assertions.assertSame(dateNow, dateNow.withMonth(3));
    // Assertions.assertNotSame(dateNow, dateNow.withMonth(3));

    Assertions.assertThrows(DateTimeException.class, () -> {
      dateNow.withMonth(13);
    });
  }

  @Test
  void getValues() {

    LocalDate now = LocalDate.now();
    System.out.println(now.getYear());
    System.out.println(now.getMonth());
    System.out.println(now.getDayOfMonth());
    System.out.println(now.getDayOfWeek());
    System.out.println(now.getDayOfYear());
    System.out.println(now.getEra());
    System.out.println(now.getChronology());
    System.out.println(now.getMonthValue());
  }
}
