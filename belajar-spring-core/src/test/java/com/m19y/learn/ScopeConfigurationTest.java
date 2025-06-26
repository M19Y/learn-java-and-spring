package com.m19y.learn;

import com.m19y.learn.data.Bar;
import com.m19y.learn.data.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ScopeConfigurationTest {


  @Test
  void testScopePrototype() {
    ApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfiguration.class);

    Foo foo1 = context.getBean(Foo.class);
    Foo foo2 = context.getBean(Foo.class);
    Foo foo3 = context.getBean(Foo.class);

    assertNotSame(foo1, foo2);
    assertNotSame(foo2, foo3);
    assertNotSame(foo3, foo1);

  }

  @Test
  void testScopeDoubleton() {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScopeConfiguration.class);

    Bar bar1 = applicationContext.getBean(Bar.class);
    Bar bar2 = applicationContext.getBean(Bar.class);
    Bar bar3 = applicationContext.getBean(Bar.class);
    Bar bar4 = applicationContext.getBean(Bar.class);

    assertNotSame(bar1, bar2);
    assertSame(bar1, bar3);
    assertSame(bar2, bar4);
  }
}