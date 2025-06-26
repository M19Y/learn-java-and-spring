package com.m19y.learn;

import com.m19y.learn.data.Foo;
import com.m19y.learn.data.MultiFoo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { MultiFoo.class })
public class ObjectProviderConfiguration {

  @Bean
  public Foo foo1(){
    return new Foo();
  }

  @Bean
  public Foo foo3(){
    return new Foo();
  }

  @Bean
  public Foo foo2(){
    return new Foo();
  }
}
