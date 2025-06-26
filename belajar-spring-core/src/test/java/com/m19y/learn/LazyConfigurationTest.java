package com.m19y.learn;

import com.m19y.learn.data.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class LazyConfigurationTest {

  @Test
  void lazyTest1() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(LazyConfiguration.class);
    // bean foo should not be created because it was lazy
  }

  @Test
  void lazyTest2() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(LazyConfiguration.class);
    Foo foo = applicationContext.getBean(Foo.class);
    // bean foo should be created because we call it
  }
}