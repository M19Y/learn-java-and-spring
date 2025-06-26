package com.m19y.learn;

import com.m19y.learn.data.Foo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class DuplicateTest {


  @Test
  void shouldThrowNoUniqueBeanDefinitionException() {

    ApplicationContext context = new AnnotationConfigApplicationContext(DuplicateConfiguration.class);

    NoUniqueBeanDefinitionException ex = Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
      Foo foo = context.getBean(Foo.class);
    });

    log.error(ex.getMessage());
  }

  @Test
  void getUniqueBeanWithDifferentName() {

    ApplicationContext context = new AnnotationConfigApplicationContext(DuplicateConfiguration.class);

    Foo foo1 = context.getBean("foo1", Foo.class);
    Foo foo2 = context.getBean("foo2", Foo.class);

    Assertions.assertNotSame(foo1, foo2);
  }
}
