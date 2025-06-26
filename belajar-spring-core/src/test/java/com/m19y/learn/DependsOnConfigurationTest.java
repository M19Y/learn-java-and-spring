package com.m19y.learn;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class DependsOnConfigurationTest {

  @Test
  void dependOnTest() {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DependsOnConfiguration.class);
  }
}