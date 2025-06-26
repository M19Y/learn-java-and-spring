package com.m19y.learn;

import com.m19y.learn.data.Bar;
import com.m19y.learn.data.Foo;
import com.m19y.learn.imports.MainConfigurations;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportConfigurationTest {


  @Test
  void testImport() {

    ApplicationContext context = new AnnotationConfigApplicationContext(MainConfigurations.class);

    Foo foo = context.getBean(Foo.class);
    Bar bar = context.getBean(Bar.class);
  }
}
