package com.m19y.learn;

import com.m19y.learn.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ComponentConfigurationTest {

  @Test
  void componentScanTest() {

    ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfiguration.class);

    ProductService productService1 = context.getBean(ProductService.class);
    ProductService productService2 = context.getBean("productService", ProductService.class);

    assertSame(productService1, productService2);
  }
}