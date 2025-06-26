package com.m19y.learn;

import com.m19y.learn.data.Car;
import com.m19y.learn.processor.IdGeneratorPostProcessor;
import com.m19y.learn.processor.PrefixIdGeneratorPostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class OrderedBeanPostProcessorTest {

  @Configuration
  @Import({
          IdGeneratorPostProcessor.class,
          PrefixIdGeneratorPostProcessor.class,
          Car.class
  })
  private static class TestConfiguration{
    
  }

  @Test
  void testOrdered() {

    ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
    Car car = context.getBean(Car.class);

    System.out.println(car.getId());
    Assertions.assertNotNull(car.getId());
    Assertions.assertTrue(car.getId().startsWith("Prefix-"));
  }
}
