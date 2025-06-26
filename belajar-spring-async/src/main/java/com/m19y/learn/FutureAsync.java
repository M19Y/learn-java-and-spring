package com.m19y.learn;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
@Slf4j
public class FutureAsync {

  // kita bisa memberitahu Executor mana yang akan diggunakan oleh Async-nya
  // by default dia akan menggunakan taskExecutor
  @Async(value = "singleTaskExecutor")
  @SneakyThrows
  public Future<String> hello(final String name){
    CompletableFuture<String> future = new CompletableFuture<>();
    Thread.sleep(Duration.ofSeconds(2));
    future.complete("Hello " + name + " from thread " + Thread.currentThread());
    return future;
  }

}
