package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.*;

public class YearYearMonthMonthDayTest {

  @Test
  void create() {
    Year year = Year.now();
    Year yearOf = Year.of(2001);
    Year yearParse = Year.parse("2010"); // format yyyy

    YearMonth yearMonth = YearMonth.now();
    YearMonth yearMonthOf = YearMonth.of(2001, Month.SEPTEMBER);
    YearMonth yearMonthParse = YearMonth.parse("2012-12"); // format yyyy-MM

    MonthDay monthDay = MonthDay.now();
    MonthDay monthDayOf = MonthDay.of(Month.SEPTEMBER, 11);
    MonthDay monthDayParse = MonthDay.parse("--12-12");// format --MM-dd

    System.out.println("===== Year =====");
    System.out.println(year);
    System.out.println(yearOf);
    System.out.println(yearParse);

    System.out.println("\n===== YearMonth =====");
    System.out.println(yearMonth);
    System.out.println(yearMonthOf);
    System.out.println(yearMonthParse);

    System.out.println("\n===== MonthDay =====");
    System.out.println(monthDay);
    System.out.println(monthDayOf);
    System.out.println(monthDayParse);
  }

  @Test
  void conversion() {

    Year year = Year.of(2001);
    YearMonth yearMonth = year.atMonth(Month.SEPTEMBER);
    LocalDate localDate = yearMonth.atDay(11);
    MonthDay monthDay = MonthDay.from(localDate);

  }
}
