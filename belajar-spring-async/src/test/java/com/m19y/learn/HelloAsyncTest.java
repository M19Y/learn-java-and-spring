package com.m19y.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@Slf4j
@SpringBootTest
public class HelloAsyncTest {


  @Autowired
  private HelloAsync helloAsync;

  @Test
  void shouldLogMessageBeforeInvokeHello_whenAsyncHelloMethodCalled() throws InterruptedException {

    // default threadnya adalah 8
    for (int i = 0; i < 17; i++) {
      helloAsync.hello();
    }

    log.info("after invoke hello()");
    Thread.sleep(Duration.ofSeconds(5));
  }
}
