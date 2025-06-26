package com.m19y.learn;

import com.m19y.learn.data.Foo;
import com.m19y.learn.processor.FooBeanFactoryPostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanFactoryPostProcessorTest {

  @Test
  void createBeanTest() {

    ApplicationContext context = new AnnotationConfigApplicationContext(FooBeanFactoryPostProcessor.class);

    Foo foo1 = context.getBean(Foo.class);
    Foo foo2 = context.getBean(Foo.class);

    Assertions.assertNotNull(foo1);
    Assertions.assertNotNull(foo2);

    Assertions.assertSame(foo1, foo2);
  }
}
