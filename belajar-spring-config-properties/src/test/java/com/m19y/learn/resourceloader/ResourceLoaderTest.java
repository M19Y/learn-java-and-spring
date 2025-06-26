package com.m19y.learn.resourceloader;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class ResourceLoaderTest {

  @Autowired
  TestApplication.SampleResource sampleResource;

  @Test
  void test() throws IOException {
    System.out.println(sampleResource.getText().trim());
    Assertions.assertEquals("This is a resource file", sampleResource.getText().trim());
  }

  @SpringBootApplication
  private static class TestApplication{
    
    @Component
    private static class SampleResource implements ResourceLoaderAware {

      @Setter
      private ResourceLoader resourceLoader;

      public String getText() throws IOException{
        Resource resource = resourceLoader.getResource("classpath:/text/resource.txt");
        try(InputStream inputStream = resource.getInputStream()){
          return new String(inputStream.readAllBytes());
        }

      }
    }
  }
}
