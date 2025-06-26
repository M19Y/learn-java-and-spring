package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.zone.ZoneRulesException;
import java.util.Set;

public class ZoneIdAndZoneOffSetTest {

  @Test
  void createZoneId() {

    ZoneId zoneIdDefault = ZoneId.systemDefault();
    System.out.println(zoneIdDefault);

    ZoneId jakarta = ZoneId.of("Asia/Jakarta");
    System.out.println(jakarta);

    Assertions.assertThrows(ZoneRulesException.class, () -> {
      ZoneId.of("Asia/Kendari");

    });

    Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();

    System.out.println("\n===== Available zone id =====");
    for(String id: availableZoneIds){
      System.out.println(id);
    }

  }

  @Test
  void createZoneOffSet() {

    ZoneOffset zoneOffsetOf = ZoneOffset.of("+07:00");
    ZoneOffset zoneOffsetOfHours1 = ZoneOffset.ofHours(7);
    ZoneOffset zoneOffsetOfHours2 = ZoneOffset.ofHours(-2);
    ZoneOffset zoneOffsetOfHoursMinutes = ZoneOffset.ofHoursMinutes(8, 46);

    System.out.println(zoneOffsetOf);
    System.out.println(zoneOffsetOfHours1);
    System.out.println(zoneOffsetOfHours2);
    System.out.println(zoneOffsetOfHoursMinutes);


  }
}
