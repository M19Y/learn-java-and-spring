package com.m19y.learn;

import com.m19y.learn.data.Bar;
import com.m19y.learn.data.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentScanConfigurationTest {


  @Test
  void testImport() {

    ApplicationContext context = new AnnotationConfigApplicationContext(ScanConfiguration.class);

    Foo foo = context.getBean(Foo.class);
    Bar bar = context.getBean(Bar.class);
  }
}
