package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.*;

public class OffsetTimeAndOffsetDateTimeTest {

  @Test
  void createOffsetTime() {

    OffsetTime now = OffsetTime.now();
    OffsetTime offsetTimeWithId = OffsetTime.now(ZoneId.of("Asia/Jakarta"));
    OffsetTime offsetTimeOf = OffsetTime.of(LocalTime.now(), ZoneOffset.ofHours(5));
    OffsetTime parsing = OffsetTime.parse("20:20:03+10:00");

    System.out.println(now);
    System.out.println(offsetTimeWithId);
    System.out.println(offsetTimeOf);
    System.out.println(parsing);

  }

  @Test
  void createOffsetDateTime() {

    OffsetDateTime now = OffsetDateTime.now();
    OffsetDateTime offsetDateTimeOf = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(7));
    OffsetDateTime parse1 = OffsetDateTime.parse("2025-03-10T21:23:36.899544700+07:00");
    OffsetDateTime parse2 = OffsetDateTime.parse("2025-03-10T21:23:36.899544700+07:00");
    System.out.println(now);
    System.out.println(offsetDateTimeOf);
    System.out.println(parse1);
    System.out.println(parse2);
    
  }

  @Test
  void conversionOffsetTime() {

    // between localTime and OffsetTime

    // localtime to OffsetTime
    LocalTime localTime1 = LocalTime.now();
    System.out.println(localTime1);

    OffsetTime offsetTime = localTime1.atOffset(ZoneOffset.ofHours(8));
    System.out.println(offsetTime);

    // OffsetTime to localtime
    LocalTime localTime2 = offsetTime.toLocalTime();
    System.out.println(localTime2);

  }

  @Test
  void conversionOffsetDateTime() {

    // localDateTime to OffsetDateTime
    LocalDateTime localDateTime1 = LocalDateTime.now();
    System.out.println(localDateTime1);

    OffsetDateTime offsetDateTime = localDateTime1.atOffset(ZoneOffset.ofHours(8));
    System.out.println(offsetDateTime);

    // OffsetDateTime to localDateTime
    LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();
    System.out.println(localDateTime);

  }
}
