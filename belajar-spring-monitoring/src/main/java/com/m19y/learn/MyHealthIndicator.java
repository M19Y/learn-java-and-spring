package com.m19y.learn;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator extends AbstractHealthIndicator {

  @Override
  protected void doHealthCheck(Health.Builder builder) throws Exception {
    // jika status ini Status.DOWN maka status aplikasi kita akan DOWN
    builder.status(Status.UP).withDetail("app", "OK").withDetail("error", "NO ERROR");
  }
}
