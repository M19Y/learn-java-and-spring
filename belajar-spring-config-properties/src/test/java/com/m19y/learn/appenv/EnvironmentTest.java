package com.m19y.learn.appenv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class EnvironmentTest {

  @Autowired
  private Environment environment;

  @Test
  void getJavaHomeTroughEnv() {
    String javaHome = environment.getProperty("JAVA_HOME");
    System.out.println(javaHome);
    Assertions.assertEquals("D:\\Tools\\jdk-21.0.5", javaHome);
  }

  @Test
  void getAllProperties() {
    System.getProperties().forEach((k, v) -> {
      System.out.println(k + " : " + v);
    });

  }

  @SpringBootApplication
  protected static class TestApplication{

  }
}
