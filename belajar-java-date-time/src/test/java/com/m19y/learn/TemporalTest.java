package com.m19y.learn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;

public class TemporalTest {

  private final Temporal localDateTime = LocalDateTime.now();

  @BeforeEach
  void setUp() {
    System.out.println(localDateTime);
  }

  @Test
  void create() {

    Temporal localTime = LocalTime.now();
    System.out.println(localTime);
    Temporal zonedDateTime = ZonedDateTime.now();
    System.out.println(zonedDateTime);
  }

  @Test
  void temporalAmount() {
    Temporal newDateTime = localDateTime.plus(Period.ofDays(10));
    System.out.println(newDateTime);
  }

  @Test
  void temporalUnit() {
    Temporal newMouth = localDateTime.plus(2, ChronoUnit.MONTHS);
    System.out.println(newMouth);

    long between = ChronoUnit.MINUTES.between(LocalTime.now(), LocalTime.now().plusHours(10));
    System.out.println(between);
  }

  @Test
  void temporalField() {
    // simple way
    LocalDateTime simpleTime = LocalDateTime.now();
    System.out.println(simpleTime.getYear());

    // with extra step
    Temporal temporal = LocalDateTime.now();
    System.out.println(temporal.get(ChronoField.YEAR));

    // another field we can use is
    System.out.println(temporal.get(ChronoField.MONTH_OF_YEAR));
    System.out.println(temporal.get(ChronoField.DAY_OF_MONTH));
    System.out.println(temporal.get(ChronoField.HOUR_OF_DAY));
    System.out.println(temporal.get(ChronoField.SECOND_OF_MINUTE));

    // ... the rest wil be same
  }

  @Test
  void temporalQuery() {

    List<Integer> time = localDateTime.query((TemporalQuery<List<Integer>>) temporal -> {
      ArrayList<Integer> time1 = new ArrayList<>();
      time1.add(temporal.get(ChronoField.YEAR));
      time1.add(temporal.get(ChronoField.MONTH_OF_YEAR));
      time1.add(temporal.get(ChronoField.DAY_OF_MONTH));
      return time1;
    });

    List<Integer> time2 = localDateTime.plus(48, ChronoUnit.HOURS).query(temporal ->
        List.of(temporal.get(ChronoField.YEAR),
        temporal.get(ChronoField.MONTH_OF_YEAR),
        temporal.get(ChronoField.DAY_OF_MONTH)));
    System.out.println(time);
    System.out.println(time2);
  }

  @Test
  void temporalAdjuster() {

    LocalDate localDate = LocalDate.now();
    LocalDate localDate1 = localDate.with(TemporalAdjusters.firstDayOfMonth());
    LocalDate localDate2 = localDate.with(TemporalAdjusters.firstDayOfYear());
    LocalDate localDate3 = localDate.with(TemporalAdjusters.lastDayOfYear());
    LocalDate localDate4 = localDate.with(TemporalAdjusters.lastDayOfMonth());

    System.out.println(localDate);
    System.out.println(localDate1);
    System.out.println(localDate2);
    System.out.println(localDate3);
    System.out.println(localDate4);
  }
}
