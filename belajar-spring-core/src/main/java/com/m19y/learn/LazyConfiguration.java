package com.m19y.learn;

import com.m19y.learn.data.Bar;
import com.m19y.learn.data.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Configuration
public class LazyConfiguration {

  @Bean
  @Lazy
  @DependsOn(value = {"bar"})
  public Foo foo(){
    log.info("Create bean foo");
    return new Foo();
  }

  @Bean
  public Bar bar(){
    log.info("Create bean bar");
    return new Bar();
  }
}
