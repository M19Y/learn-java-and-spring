package com.m19y.learn.profileenvironment;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = {"local", "production"})
public class ProfileEnvironmentTest {


  @Autowired
  TestApp.SampleApp sampleApp;

  @Test
  void testGetAllActiveProfilesUsingEnvironment() {

    Assertions.assertArrayEquals(new String[]{"local", "production"}, sampleApp.getProfiles());

  }

  @SpringBootApplication
  protected static class TestApp{

    @Component
    protected static class SampleApp implements EnvironmentAware {

      @Setter
      private Environment environment;

      public String[] getProfiles(){
        return environment.getActiveProfiles();
      }
    }
  }
}
