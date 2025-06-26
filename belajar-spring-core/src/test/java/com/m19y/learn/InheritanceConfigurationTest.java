package com.m19y.learn;

import com.m19y.learn.service.MerchantService;
import com.m19y.learn.service.MerchantServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class InheritanceConfigurationTest {

  @Test
  void testInheritance() {

    ApplicationContext context = new AnnotationConfigApplicationContext(InheritanceConfiguration.class);

    MerchantServiceImpl merchantServiceImpl = context.getBean(MerchantServiceImpl.class);
    MerchantService merchantService = context.getBean(MerchantService.class);

    assertSame(merchantServiceImpl, merchantService);
  }
}