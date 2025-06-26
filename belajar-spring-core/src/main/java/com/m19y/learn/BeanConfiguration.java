package com.m19y.learn;

import com.m19y.learn.data.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BeanConfiguration {

  @Bean // secara default bean akan mengubah method ini menjadi singleton
  public Foo foo(){
    log.info("Create Foo object with name foo");
    return new Foo();
  }
}
