package com.m19y.learn.runner;

import com.m19y.learn.properties.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ApplicationPropertiesRunner implements ApplicationRunner {

  private ApplicationProperties properties;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info(properties.getOwner());
    log.info(properties.getName());
    log.info(String.valueOf(properties.getVersion()));
    log.info(String.valueOf(properties.getProductionMode()));
  }
}
