package com.m19y.learn;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloAsync {

  @Async
//  @SneakyThrows
  public void hello() throws InterruptedException {
    Thread.sleep(2_000L);
    log.info("hello after 2 sec with thread {}", Thread.currentThread());
  }
}
