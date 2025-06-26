package com.m19y.learn.scan;

import com.m19y.learn.data.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooConfiguration {

  @Bean
  public Foo foo(){return new Foo();}
}
