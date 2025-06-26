package com.m19y.learn.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootTest
public class ValueApplicationTest {

  @Autowired
  TestApplication.ApplicationProperties properties;

  @Autowired
  TestApplication.SystemProperties systemProperties;


  @Test
  void envPropertiesTest() {
    Assertions.assertEquals("m19y", properties.getOwner());
    Assertions.assertEquals(1, properties.getVersion());
    Assertions.assertFalse(properties.isProductionMode());
  }

  @Test
  void systemPropertiesTest() {
    Assertions.assertEquals("D:\\Tools\\jdk-21.0.5", systemProperties.getJavaHome());
  }

  @SpringBootApplication
  protected static class TestApplication{


    @Getter
    @Component
    protected static class ApplicationProperties{
      @Value("${application.version}")
      private Integer version;

      @Value("${application.owner}")
      private String owner;

      @Value("${application.production-mode}")
      private boolean isProductionMode;

    }

    @Getter
    @Component
    protected static class SystemProperties{
      @Value("${JAVA_HOME}")
      private String javaHome;

    }
  }
}

