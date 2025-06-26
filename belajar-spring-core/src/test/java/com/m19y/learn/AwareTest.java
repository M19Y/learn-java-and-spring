package com.m19y.learn;

import com.m19y.learn.service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class AwareTest {

  @Configuration
  @Import({AuthService.class})
  private static class TestConfiguration{

  }

  @Test
  void testAware() {

    ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);

    AuthService authService = applicationContext.getBean(AuthService.class);

    Assertions.assertEquals("com.m19y.learn.service.AuthService", authService.getBeanName());
    Assertions.assertNotNull(authService.getApplicationContext());
    Assertions.assertSame(authService.getApplicationContext(), applicationContext);


  }
}
