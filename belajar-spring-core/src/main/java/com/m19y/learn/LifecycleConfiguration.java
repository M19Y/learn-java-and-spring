package com.m19y.learn;

import com.m19y.learn.data.Connection;
import com.m19y.learn.data.Server;
import com.m19y.learn.data.Server2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LifecycleConfiguration {

  @Bean
  public Connection connection(){
    log.info("create connection");
    return new Connection();
  }

  // using bean parameter in bean method
  @Bean(initMethod = "start", destroyMethod = "stop")
  public Server server(){
    log.info("create server");
    return new Server();
  }

  // using PreConstruct and PostDestroy
  @Bean
  public Server2 server2(){
    log.info("create server2");
    return new Server2();
  }
}
