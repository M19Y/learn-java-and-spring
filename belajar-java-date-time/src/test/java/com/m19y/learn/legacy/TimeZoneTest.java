package com.m19y.learn.legacy;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneTest {

  private static final Logger log = LoggerFactory.getLogger(TimeZoneTest.class);


  @Test
  void timeZoneCheck() {

    log.info(TimeZone.getDefault().toString());

    for(String id : TimeZone.getAvailableIDs()){
      System.out.println(id);
    }

  }

  @Test
  void timeZoneTest() {

    TimeZone local = TimeZone.getDefault();
    System.out.println(local);
    TimeZone jakarta = TimeZone.getTimeZone("Asia/Jakarta");
    System.out.println(jakarta);

  }

  @Test
  void calendarWithTimeZone() {

    Calendar local = Calendar.getInstance(); // default time zone
    Calendar jakarta = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta"));

    log.info("my local hour is -> {}", local.get(Calendar.HOUR));
    log.info("jakarta hour is -> {}", jakarta.get(Calendar.HOUR));

    // tidak akan berubah ketika kita langsung menggunakan object Date
    Date dateLocal = local.getTime();
    Date dateJakarta = local.getTime();

    log.info("my local date is -> {}", dateLocal);
    log.info("jakarta date is -> {}", dateJakarta);

  }
}
