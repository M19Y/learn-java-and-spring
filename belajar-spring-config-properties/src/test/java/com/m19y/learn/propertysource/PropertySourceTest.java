package com.m19y.learn.propertysource;

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

@SpringBootTest(classes = PropertySourceTest.TestApplication.class)
public class PropertySourceTest {

  @Autowired
  TestApplication.SampleProperties property;

  @Test
  void propertySourceTest() {

    Assertions.assertEquals("sample-app-test", property.getName());
    Assertions.assertEquals(1, property.getVersion());

  }

  // penambahan property peroperty source ini tidak bertujuan
  // untuk membuat file baru, tapi akan di merge oleh spring nantinya ke application.properties
  // oleh karena itu key dari propertiesnya harus lah unique
  @SpringBootApplication
  @PropertySources(value = {
          @PropertySource("classpath:/sample.properties")
  })
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

