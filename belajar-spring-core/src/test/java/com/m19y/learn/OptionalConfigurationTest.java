package com.m19y.learn;

import com.m19y.learn.data.Foo;
import com.m19y.learn.data.FooBar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class OptionalConfigurationTest {

  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(OptionalConfiguration.class);
    context.registerShutdownHook();
  }

  @Test
  void testOptionalParameter() {

    Foo foo = context.getBean(Foo.class);
    FooBar fooBar = context.getBean(FooBar.class);

    assertNull(fooBar.getBar());
    assertSame(foo, fooBar.getFoo());
  }
}