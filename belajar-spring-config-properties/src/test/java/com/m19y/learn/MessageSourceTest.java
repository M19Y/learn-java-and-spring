package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;


public class MessageSourceTest {

  ApplicationContext context;
  MessageSource messageSource;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(TestApplication.class);
    messageSource = context.getBean(MessageSource.class);
  }

  @Test
  void testDefaultLocale() {
    String message = messageSource.getMessage("hello", new Object[]{"Bro"}, Locale.ENGLISH);
    Assertions.assertEquals("hello Bro", message);
  }

  @Test
  void testIndonesiaLocal() {
    String message = messageSource.getMessage("hello", new Object[]{"Bro"}, Locale.of("in_ID"));
    Assertions.assertEquals("halo Bro", message);
  }

  @Configuration
  protected static class TestApplication{

    @Bean
    public MessageSource messageSource(){
      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      messageSource.setBasenames("my"); // akan membaca my.properties dan my_in_ID.properties di resource
      return messageSource;
    }
  }
}
