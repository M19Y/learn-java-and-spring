package com.m19y.learn.sbmessagesource;


import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@SpringBootTest(classes = SBMessageSourceTest.TestApplication.class)
public class SBMessageSourceTest {

  @Autowired
  private TestApplication.SampleSource sampleSource;

  @Test
  void testMessages() {
    String messageDefault = sampleSource.sleep(Locale.getDefault(), "Bro");
    Assertions.assertEquals("Bro is sleeping", messageDefault);
    String messageLocalIn = sampleSource.sleep(Locale.of("in_ID"), "Bro");
    Assertions.assertEquals("Bro sedang tidur", messageLocalIn);
  }

  @SpringBootApplication
  public static class TestApplication{

    @Component
    public static class SampleSource implements MessageSourceAware {

      @Setter
      private MessageSource messageSource;

      public String sleep(Locale locale, String name){
        return messageSource.getMessage("sleep", new Object[]{name}, locale);
      }
    }
  }


}
