package com.m19y.learn;

import com.m19y.learn.data.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class BeanNameConfigurationTest {

  @Test
  void changeBeanNameTest() {
    ApplicationContext context = new AnnotationConfigApplicationContext(BeanNameConfiguration.class);
    Foo primary = context.getBean(Foo.class);
    Foo first = context.getBean("fooFirst", Foo.class);
    Foo second = context.getBean("fooSecond", Foo.class);

    assertSame(primary, first);
    assertNotSame(first, second);
    assertNotSame(primary, second);
  }
}
