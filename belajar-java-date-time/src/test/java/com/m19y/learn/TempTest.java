package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TempTest {

  @Test
  void testHijrahDate() {

    // Ambil tanggal Masehi saat ini
    LocalDate today = LocalDate.now();

    // Konversi ke HijrahDate
    HijrahDate hijriToday = HijrahDate.from(today);

    // Cetak tanggal Hijriah
    System.out.println("Tanggal Masehi: " + today);
    System.out.println("Tanggal Hijriah: " + hijriToday);

    LocalDate gregorianDate = LocalDate.from(hijriToday);

    System.out.println("Tanggal Masehi yang setara: " + gregorianDate);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy 'H'", Locale.ENGLISH);

    // Format dan cetak hasil
    String formattedDate = hijriToday.format(formatter);
    System.out.println("Formatted Hijri Date: " + formattedDate);
  }

  @Test
  void getValue() {

    Year year = Year.now();
    System.out.println(year.getValue());

    YearMonth yearMonth = YearMonth.now();
    System.out.println(yearMonth.getYear());
    System.out.println(yearMonth.getMonthValue());

    MonthDay monthDay = MonthDay.now();
    System.out.println(monthDay.getMonthValue());
    System.out.println(monthDay.getDayOfMonth());
  }
}
