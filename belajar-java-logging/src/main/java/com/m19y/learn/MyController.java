package com.m19y.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyController {

  private static final Logger log = LoggerFactory.getLogger(MyController.class);
  private final MyService service;

  public MyController(MyService service) {
    this.service = service;
  }

  // before MDC
  public void save(String requestId){
    log.info("{} - controller save", requestId);
    this.service.save(requestId);
  }

  // after MDC
  public void save(){
    log.info("controller save");
    this.service.save();
  }
}
