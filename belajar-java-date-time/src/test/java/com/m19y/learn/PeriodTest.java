package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class PeriodTest {

  @Test
  void create() {
    Period period1 = Period.ofDays(11);
    Period period2 = Period.ofMonths(9);
    Period period3 = Period.ofWeeks(2);
    Period period4 = Period.ofYears(21);
    Period period5 = Period.of(21, 9,11);

    System.out.println(period1);
    System.out.println(period2);
    System.out.println(period3);
    System.out.println(period4);
    System.out.println(period5);

  }

  @Test
  void getValues() {

    Period period = Period.of(21,9,11);
    System.out.println(period.getYears());
    System.out.println(period.getMonths());
    System.out.println(period.getDays());

  }

  @Test
  void useBetween() {
    Period period = Period.between(LocalDate.of(2001, Month.SEPTEMBER, 11), LocalDate.now());
    System.out.println(period.getYears());
    System.out.println(period.getMonths());
    System.out.println(period.getDays());
  }
}

