package com.m19y.learn.profileproperties;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {ProfilePropertiesTest.TestApplication.class})
@ActiveProfiles(value = {"production", "test"})
public class ProfilePropertiesTest {

  @Autowired
  TestApplication.SampleTest sampleTest;

  @Test
  void profileFilePropertiesTest() {

    Assertions.assertEquals("Test", sampleTest.getProfileTest());
    Assertions.assertEquals("Default", sampleTest.getProfileDefault());
    Assertions.assertEquals("Production", sampleTest.getProfileProduction());
  }

  @SpringBootApplication
  public static class TestApplication{

    @Component
    @Getter
    public static class SampleTest{

      @Value("${profile.default}")
      private String profileDefault;

      @Value("${profile.production}")
      private String profileProduction;

      @Value("${profile.test}")
      private String profileTest;
    }
  }
}
