package com.m19y.learn;

import com.m19y.learn.data.Bar;
import com.m19y.learn.data.Foo;
import com.m19y.learn.data.FooBar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependencyInjectionTest {

  ApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(DependencyInjectionConfiguration.class);
  }

  @Test
  void testNoDI() {

    Foo foo = new Foo();
    Bar bar = new Bar();

    FooBar fooBar = new FooBar(foo, bar);

    Assertions.assertSame(foo, fooBar.getFoo());
    Assertions.assertSame(bar, fooBar.getBar());
  }

  @Test
  void testWithDI() {

    Foo foo = context.getBean(Foo.class); // primary
    Foo fooSecond = context.getBean("fooSecond", Foo.class);
    Bar bar = context.getBean(Bar.class);

    FooBar fooBar = context.getBean(FooBar.class);

    Assertions.assertNotSame(foo, fooBar.getFoo());
    Assertions.assertSame(fooSecond, fooBar.getFoo());
    Assertions.assertSame(bar, fooBar.getBar());

  }
}
