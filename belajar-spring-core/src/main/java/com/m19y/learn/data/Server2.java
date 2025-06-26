package com.m19y.learn.data;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server2 {


  @PostConstruct
  public void start(){ log.info("start server2");}
  @PreDestroy
  public void stop(){ log.info("stop server2");}
}
