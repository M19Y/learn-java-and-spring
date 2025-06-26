package com.m19y.learn.profile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfileTest.TestApplication.class)
@ActiveProfiles(profiles = {"local"}) // memaksa profilenya menjadi local, tanpa peduli profile apa yang ada di app.properties
public class ProfileTest {

  @Autowired
  TestApplication.SayHello sayHello;

  @Test
  void testProfile() {
    Assertions.assertEquals("Hello Bro from local", sayHello.sayHello("Bro"));
  }

  @SpringBootApplication
  protected static class TestApplication{

    public interface SayHello {
      String sayHello(String name);
    }

    @Component
    @Profile(value = {"local"})
    public static class SayHelloLocal implements SayHello{

      @Override
      public String sayHello(String name) {
        return "Hello " + name + " from local";
      }
    }

    @Component
    @Profile(value = {"production"})
    public static class SayHelloProduction implements SayHello{

      @Override
      public String sayHello(String name) {
        return "Hello " + name + " from production";
      }
    }
  }
}
