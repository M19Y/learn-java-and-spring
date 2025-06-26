package com.m19y.learn;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyScheduleTask {

  // untuk membuat custom matric kita bisa menambhakan meterRegistry untuk menambahkan propertynya
  @Autowired
  private MeterRegistry meterRegistry;

  @Scheduled(fixedRate = 10L, timeUnit = TimeUnit.SECONDS)
  public void hello(){
    meterRegistry.counter("my.schedule.task").increment();
    log.info("hello schedule");
  }

}
