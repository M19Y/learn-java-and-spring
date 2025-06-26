package com.m19y.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class FutureAsyncTest {

  @Autowired
  private FutureAsync futureAsync;

  @Test
  void shouldReturnFutureResponseAfter2Seconds_whenHelloMethodCalled() throws ExecutionException, InterruptedException {

    log.info("before call hell()");
    Future<String> future = futureAsync.hello("Bro");
    log.info("after call hell()");
    String response = future.get();
    log.info("Response => {}", response );

  }
}