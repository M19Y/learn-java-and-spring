package com.m19y.learn;

import com.m19y.learn.data.Bar;
import com.m19y.learn.data.Foo;
import com.m19y.learn.scope.DoubletonScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Slf4j
@Configuration
public class ScopeConfiguration {

  @Bean
  @Scope(scopeName = "prototype")
  public Foo foo(){
    log.info("create new foo");
    return new Foo();
  }

  @Bean
  @Scope(scopeName = "doubleton")
  public Bar bar(){
    log.info("create new bar");
    return new Bar();
  }

  @Bean
  public CustomScopeConfigurer customScopeConfigurer(){
    CustomScopeConfigurer config = new CustomScopeConfigurer();
    config.addScope("doubleton", new DoubletonScope());
    return config;
  }

}
