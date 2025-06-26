package com.m19y.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRepository {

  private static final Logger log = LoggerFactory.getLogger(MyRepository.class);

  // Before MDC
  public void save(String requestId){
    log.info("{} - repository save", requestId);
  }

  // After MDC
  public void save(){
    log.info("repository save");
  }
}
