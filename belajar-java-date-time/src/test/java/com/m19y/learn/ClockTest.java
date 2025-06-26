package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.*;

public class ClockTest {

  @Test
  void create() {

    Clock utc = Clock.systemUTC();
    System.out.println(utc);

    Clock currentZone = Clock.systemDefaultZone();
    System.out.println(currentZone);

    Clock costumeZone = Clock.system(ZoneId.of("Asia/Jayapura"));
    System.out.println(costumeZone);
  }

  @Test
  void instant() throws InterruptedException {
    Clock clock = Clock.system(ZoneId.of("Asia/Tokyo"));

    Instant instant1 = clock.instant();
    System.out.println(instant1);

    Thread.sleep(1_000);
    Instant instant2 = clock.instant();
    System.out.println(instant2);
  }

  @Test
  void fromClock() {
    Clock clock = Clock.system(ZoneId.of("Asia/Jakarta"));
    System.out.println(clock);

    Year year = Year.now(clock);
    YearMonth yearMonth = YearMonth.now(clock);
    MonthDay monthDay = MonthDay.now(clock);
    LocalTime localTime = LocalTime.now(clock);
    LocalDateTime localDateTime = LocalDateTime.now(clock);
    ZonedDateTime zonedDateTime = ZonedDateTime.now(clock);
    OffsetTime offsetTime = OffsetTime.now(clock);
    OffsetDateTime offsetDateTime = OffsetDateTime.now(clock);

    System.out.println(zonedDateTime);
    System.out.println(offsetTime);
    System.out.println(offsetDateTime);
  }
}
