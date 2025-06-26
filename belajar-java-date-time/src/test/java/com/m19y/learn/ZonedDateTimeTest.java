package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.*;

public class ZonedDateTimeTest {

  @Test
  void create() {

    ZonedDateTime now = ZonedDateTime.now();
    ZonedDateTime jakarta = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));
    ZonedDateTime gmt = ZonedDateTime.now(ZoneId.of("GMT"));
    ZonedDateTime tokyo = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Tokyo"));
    ZonedDateTime plus5 = ZonedDateTime.now(ZoneOffset.ofHours(5));

    ZonedDateTime parse1 = ZonedDateTime.parse("2025-03-10T20:44:26.096322+08:00[Asia/Makassar]");
    ZonedDateTime parse2 = ZonedDateTime.parse("2025-03-10T20:44:26.096322+09:00[Asia/Jakarta]"); // +09:00 will be ignore
    ZonedDateTime parse3 = ZonedDateTime.parse("2025-03-10T20:44:26.096322+09:00");

    System.out.println(now);
    System.out.println(jakarta);
    System.out.println(gmt);
    System.out.println(tokyo);
    System.out.println(plus5);

    System.out.println("\nParse====");
    System.out.println(parse1);
    System.out.println(parse2);
    System.out.println(parse3);

    System.out.println("\nZone====");
    System.out.println(tokyo.getZone()); // "Asia/Tokyo"
    System.out.println(plus5.getZone()); // +05:00
  }

  @Test
  void changeZonedDateTime() {

    ZonedDateTime now = ZonedDateTime.now();
    ZonedDateTime changeZoneOnly = now.withZoneSameLocal(ZoneId.of("Asia/Tokyo")); // tanggal jam tidak diubah
    ZonedDateTime changeAll = now.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));

    System.out.println(now);
    System.out.println(changeZoneOnly);
    System.out.println(changeAll);

  }
}
