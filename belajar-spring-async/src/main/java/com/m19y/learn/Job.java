package com.m19y.learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class Job {

  private AtomicLong counter = new AtomicLong(0L);

  // akan jalan besamaan (tanpa perlu dipanggil) saat spring application ready
  @Scheduled(timeUnit = TimeUnit.SECONDS, initialDelay = 4, fixedDelay = 2)
  public void runJob(){
    Long value  = counter.incrementAndGet();
    log.info("{} run job {}", value, Thread.currentThread());
  }

  public Long getValue(){
    return counter.get();
  }

  @Scheduled(cron = "*/2 * * * * *") // run every 2 seconds
  public void cron(){
    log.info("run job cron");
  }
}
