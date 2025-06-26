package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateTimeFormatterTest {


  @Test
  void parsing() {

    // default time format
    LocalDate localDate = LocalDate.parse("2001-09-11"); // valid default format
    System.out.println(localDate);

    // invalid default time format
    Assertions.assertThrows(DateTimeParseException.class, () -> {
      LocalDate localDate1 = LocalDate.parse("2001 09 11");
      System.out.println(localDate1);
    });

    // valid costume date time format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
    LocalDate localDate2 = LocalDate.parse("2001 09 11", formatter);
    System.out.println(localDate2); // the result always be the same 2001-09-11
  }

  @Test
  void format() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
    LocalDate localDate = LocalDate.parse("2001 09 11", formatter);

    // default result
    System.out.println(localDate);

    // if we want to change the result, we can change it by using format
    String afterFormat = localDate.format(formatter);
    System.out.println(afterFormat);
  }

  @Test
  void i18n() {

    Locale locale = Locale.of("in", "ID");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM dd", locale);
    LocalDate localDate = LocalDate.now();
    System.out.println(localDate.format(formatter));
  }
}
