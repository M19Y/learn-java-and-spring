package com.m19y.learn;

import com.m19y.learn.data.Foo;
import com.m19y.learn.data.MultiFoo;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ObjectProviderConfigurationTest {

  @Test
  void testGetAllFoos() {

    ApplicationContext context = new AnnotationConfigApplicationContext(ObjectProviderConfiguration.class);

    MultiFoo multiFoo = context.getBean(MultiFoo.class);

    assertEquals(3, multiFoo.getFoos().size());
  }
}