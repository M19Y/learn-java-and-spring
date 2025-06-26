package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.*;

public class LocalDateTImeTest {

  private final LocalDateTime currentDateTime = LocalDateTime.now();

  @Test
  void createLocalDateTime() {

    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    LocalDateTime dateTimeOf1 = LocalDateTime.of(currentDate, currentTime);

    LocalDateTime dateTimeOf2 = LocalDateTime.of(2001, Month.SEPTEMBER, 11, 8, 46, 11);

    LocalDateTime dateTimeParse = LocalDateTime.parse("2010-05-25T05:25:10");

    System.out.println(currentDateTime);
    System.out.println(dateTimeOf1);
    System.out.println(dateTimeOf2);
    System.out.println(dateTimeParse);
  }

  @Test
  void changeDateTimeUsingWithMethod() {

    LocalDateTime localDateTime1 = currentDateTime.withHour(10);
    LocalDateTime localDateTime2 = currentDateTime.withYear(10);

    System.out.println(localDateTime1);
    System.out.println(localDateTime2);
    //... the rest will be same
  }

  @Test
  void changeDateTimeUsingPlusMinusMethod(){
    LocalDateTime localDateTime1 = currentDateTime.plusHours(10);
    LocalDateTime localDateTime2 = currentDateTime.plusYears(4);

    System.out.println(localDateTime1);
    System.out.println(localDateTime2);
    //... the rest will be same
  }

  @Test
  void getValues() {
    System.out.println(currentDateTime.getYear());
    System.out.println(currentDateTime.getMonth());
    System.out.println(currentDateTime.getDayOfMonth());
    System.out.println(currentDateTime.getHour());
    System.out.println(currentDateTime.getMinute());
    System.out.println(currentDateTime.getSecond());
  }

  @Test
  void conversionBetweenLocalDateTimeAndLocalDate() {

    // localDateTime to localDate
    LocalDate localDate = LocalDateTime.now().toLocalDate();
    System.out.println(localDate);

    // localDate to localDateTIme
    LocalDateTime localDateTime1 = LocalDate.now().atTime(8, 46, 11);
    LocalDateTime localDateTime2 = localDate.atTime(LocalTime.now());

    System.out.println(localDateTime1);
    System.out.println(localDateTime2);
  }

  @Test
  void conversionBetweenLocalDateTimeAndLocalTime() {

    // localDateTime to localTime
    LocalTime localTime = LocalDateTime.now().toLocalTime();
    System.out.println(localTime);

    // localTime to localDateTime
    LocalDateTime localDateTime = localTime.atDate(LocalDate.now());
    System.out.println(localDateTime);
  }
}
