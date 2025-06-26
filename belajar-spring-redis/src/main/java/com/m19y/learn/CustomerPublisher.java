package com.m19y.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CustomerPublisher {

  @Autowired
  private StringRedisTemplate template;

  @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
  public void publishCustomer(){
    template.convertAndSend("customers", "Hello " + UUID.randomUUID());
  }
}
