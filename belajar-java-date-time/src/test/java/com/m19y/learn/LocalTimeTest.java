package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class LocalTimeTest {

  @Test
  void createLocalTime() {

    LocalTime now = LocalTime.now();
    LocalTime timeOf = LocalTime.of(4, 20, 1);
    LocalTime timeParse = LocalTime.parse("08:46:11"); // the format should be like this "HH:mm:ss.nano", the nano is optional.

    System.out.println(now);
    System.out.println(timeOf);
    System.out.println(timeParse);

  }

  @Test
  void changeTimeUsingWithMethod() {
    // Local time is immutable

    LocalTime now = LocalTime.now();
    now.withHour(20); // will be ignore
    System.out.println(now);

    LocalTime time1 = now.withHour(8);
    LocalTime time2 = now.withHour(8).withMinute(46);

    System.out.println(time1);
    System.out.println(time2);

  }

  @Test
  void changeTimeUsingPlusMinusMethod() {

    LocalTime now = LocalTime.now();

    LocalTime time1 = now.plusHours(2);
    LocalTime time2 = now.minusHours(6);
    LocalTime time3 = now.minusHours(8).plusMinutes(10);

    System.out.println(time1);
    System.out.println(time2);
    System.out.println(time3);

  }

  @Test
  void getValues() {

    LocalTime now = LocalTime.now();
    System.out.println(now.getHour());
    System.out.println(now.getMinute());
    System.out.println(now.getSecond());
    System.out.println(now.getNano());
  }
}
