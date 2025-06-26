package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.UnsupportedTemporalTypeException;

public class InstantTest {


  @Test
  void create() {

    Instant instant1 = Instant.now();
    System.out.println(instant1);

    Instant instant2 = Instant.ofEpochMilli(0);
    System.out.println(instant2);

    Instant instant3 = Instant.ofEpochMilli(1000169160000L);
    System.out.println(instant3);

  }

  @Test
  void changeInstantUsingPlusMinusMethod() {

    Instant instant1 = Instant.now();
    System.out.println(instant1);

    Instant instant2 = instant1.plusMillis(10_000);
    System.out.println(instant2);

    Instant instant3 = instant1.minusMillis(10_000);
    System.out.println(instant3);
    //... the rest will be same

  }

  @Test
  void getValues() {

    Instant now = Instant.now();

    System.out.println(now.getLong(ChronoField.NANO_OF_SECOND));
    System.out.println(now.getLong(ChronoField.INSTANT_SECONDS));


    Assertions.assertThrows(UnsupportedTemporalTypeException.class, ()-> {
      now.getLong(ChronoField.YEAR); // because Instant is not support for Year fields
      // it's only support for nano_of_second and instant_seconds
    });

    System.out.println(now.getEpochSecond());
    System.out.println(now.getNano());
    System.out.println(now.toEpochMilli());

  }

  @Test
  void conversion() {

    Instant now = Instant.now();
    // Instant to localDate

    LocalDate localDate = LocalDate.ofInstant(now, ZoneId.of("Asia/Tokyo"));
    System.out.println(localDate);

    // Instant to LocalDateTime
    LocalDateTime localDateTime = LocalDateTime.ofInstant(now, ZoneId.of("Asia/Jakarta"));
    System.out.println(localDateTime);

    // Instant to ZonedDateTime
    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, ZoneId.of("Asia/Jayapura"));
    System.out.println(zonedDateTime);

    // Instant to OffsetDateTime
    OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(now, ZoneId.of("Australia/Sydney"));
    System.out.println(offsetDateTime);


  }

  @Test
  void conversionToInstant() {

    Instant instant1 = LocalDateTime.now().toInstant(ZoneOffset.of("+07:00"));
    Instant instant2 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(9));
    Instant instant3 = ZonedDateTime.now().toInstant();
    Instant instant4 = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Australia/Sydney")).toInstant();
    Instant instant5 = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Jayapura")).toInstant();

    System.out.println(instant1);
    System.out.println(instant2);
    System.out.println(instant3);
    System.out.println(instant4);

    Assertions.assertEquals(instant2.getEpochSecond(), instant5.getEpochSecond());
  }
}
