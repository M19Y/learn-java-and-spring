package com.m19y.learn;

import com.m19y.learn.data.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class PrimaryConfigurationTest {

  @Test
  void shouldNotThrowNoUniqueBean() {

    ApplicationContext context = new AnnotationConfigApplicationContext(PrimaryConfiguration.class);

    assertDoesNotThrow(()-> {
      Foo primary = context.getBean(Foo.class);
    });
  }

  @Test
  void primaryTest() {
    ApplicationContext context = new AnnotationConfigApplicationContext(PrimaryConfiguration.class);

    Foo primary = context.getBean(Foo.class);
    Foo foo1 = context.getBean("foo1", Foo.class);
    Foo foo2 = context.getBean("foo2", Foo.class);

    assertSame(primary, foo1);
    assertNotSame(foo1, foo2);
    assertNotSame(primary, foo2);

  }
}