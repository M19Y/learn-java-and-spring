package com.m19y.learn;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobTest {

  @Autowired
  private Job job;
  @Nested
  class ScheduleTest{

    @Test
    void shouldReturnCounterValue4_whenSleep10seconds() throws InterruptedException {
      // because init takes 4 -> job start 1
      // and the rest takes 2 seconds each, it means 6 / 2 = 3
      // 1 + 3 = 4
      Thread.sleep(Duration.ofSeconds(10));
      assertEquals(4, job.getValue());
    }

    @Test
    void shouldReturnCounterValue6_whenSleep14seconds() throws InterruptedException {
      // because init takes 4 -> job start 1
      // and the rest takes 2 seconds each, it means 10 / 2 = 5
      // 1 + 5 = 6
      Thread.sleep(Duration.ofSeconds(14));
      assertEquals(6, job.getValue());
    }
  }

}