package com.m19y.learn.application;

import com.m19y.learn.data.Foo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FooApplication {

  @Bean
  public Foo foo(){
    return new Foo();
  }

//  public static void main(String[] args) {
//    ConfigurableApplicationContext context = SpringApplication.run(FooApplication.class, args);
//    Foo foo = context.getBean(Foo.class);
//    System.out.println(foo);
//  }

}
