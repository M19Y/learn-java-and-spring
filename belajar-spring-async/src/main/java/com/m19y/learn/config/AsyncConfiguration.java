package com.m19y.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// megubah Executor default dari ThreadPoolExecutor(default spring) menjadi virtualThreadExecutor
@Configuration
public class AsyncConfiguration {

  // override default
  @Bean
  public Executor taskExecutor(){
    return Executors.newVirtualThreadPerTaskExecutor();
  }

  // add a new Executor
  @Bean
  public Executor singleTaskExecutor(){
    return Executors.newSingleThreadExecutor();
  }

  // schedule
  @Bean
  public ScheduledExecutorService taskScheduler(){
    return Executors.newScheduledThreadPool(10);
  }
}
