package com.m19y.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyService {

  private static final Logger log = LoggerFactory.getLogger(MyService.class);
  private final MyRepository repository;

  public MyService(MyRepository repository) {
    this.repository = repository;
  }

  // Before MDC
  public void save(String requestId){
    log.info("{} - service save", requestId);
    this.repository.save(requestId);
  }

  // After MDC
  public void save(){
    log.info("service save");
    this.repository.save();
  }
}
