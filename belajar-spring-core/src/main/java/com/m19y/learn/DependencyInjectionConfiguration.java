package com.m19y.learn;

import com.m19y.learn.data.Bar;
import com.m19y.learn.data.Foo;
import com.m19y.learn.data.FooBar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DependencyInjectionConfiguration {

  @Bean
  @Primary
  public Foo fooFirst(){
    return new Foo();
  }

  @Bean
  public Foo fooSecond(){
    return new Foo();
  }

  @Bean
  public Bar bar(){
    return new Bar();
  }

  @Bean
  public FooBar fooBar(@Qualifier("fooSecond") Foo foo, Bar bar){
    return new FooBar(foo, bar);
  }
}
