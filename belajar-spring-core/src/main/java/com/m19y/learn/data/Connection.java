package com.m19y.learn.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public class Connection implements DisposableBean, InitializingBean {

  @Override
  public void destroy() throws Exception {
    log.info("Connection is closed");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("Connection is ready to use");
  }
}
