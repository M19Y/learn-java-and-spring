package com.m19y.learn.testpropertysource;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@TestPropertySources(
        @TestPropertySource("classpath:/sample.properties")
)
@SpringBootTest(classes = TestPropertySourceTest.TestApplication.class)
public class TestPropertySourceTest {

  @Autowired
  TestApplication.SampleProperties property;

  @Test
  void propertySourceTest() {

    Assertions.assertEquals("sample-app-test", property.getName());
    Assertions.assertEquals(1, property.getVersion());

  }

  @SpringBootApplication
  protected static class TestApplication{

    @Component
    @Getter
    protected static class SampleProperties{

      @Value("${sample.name}")
      private String name;

      @Value("${sample.version}")
      private Integer version;

    }
  }
}

