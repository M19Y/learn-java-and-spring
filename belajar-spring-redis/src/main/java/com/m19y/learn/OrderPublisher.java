package com.m19y.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class OrderPublisher {

  @Autowired
  private StringRedisTemplate template;

  @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
  public void run(){
    Order order = new Order(UUID.randomUUID().toString(), new Random().nextLong(1000, 5000));
    ObjectRecord<String, Order> record = ObjectRecord.create("orders", order);
    template.opsForStream().add(record);
  }



}
