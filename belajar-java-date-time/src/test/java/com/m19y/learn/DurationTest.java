package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

public class DurationTest {

  @Test
  void create() {

    Duration day = Duration.ofDays(11);
    Duration hour = Duration.ofHours(8);
    Duration minute = Duration.ofMinutes(46);
    Duration seconds = Duration.ofSeconds(9);
    Duration nano = Duration.ofNanos(1_000L);

    System.out.println(day);
    System.out.println(hour);
    System.out.println(minute);
    System.out.println(seconds);
    System.out.println(nano);

  }

  @Test
  void useBetween(){
    Duration duration1 = Duration.between(LocalTime.of(3, 4), LocalTime.of(5,6));
    System.out.println(duration1);
    System.out.println(duration1.toHours());
    System.out.println(duration1.toMinutes());

    Duration duration2 = Duration.between(LocalTime.now().plusHours(4), LocalTime.now());
    System.out.println(duration2);
    System.out.println(duration2.toHours());
    System.out.println(duration2.toMinutes());
  }
}
