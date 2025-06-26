package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

public class DayOfWeekTest {


  @Test
  void create() {
    DayOfWeek dayOfWeek = DayOfWeek.SUNDAY;
    System.out.println(dayOfWeek);

    DayOfWeek plushOne = dayOfWeek.plus(1);
    DayOfWeek plushTen = dayOfWeek.plus(10);

    System.out.println(plushOne);
    System.out.println(plushTen);

  }
}
