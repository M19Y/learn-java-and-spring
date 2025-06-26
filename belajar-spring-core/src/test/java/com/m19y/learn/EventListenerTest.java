package com.m19y.learn;

import com.m19y.learn.listener.LoginSuccessListener;
import com.m19y.learn.listener.SendEmailListener;
import com.m19y.learn.listener.UserListener;
import com.m19y.learn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class EventListenerTest {

  @Configuration
  @Import({
          LoginSuccessListener.class,
          UserService.class,
          SendEmailListener.class,
          UserListener.class // using annotation
  })
  private static class TestConfiguration{}

  @Test
  void testEventListener() {

    ApplicationContext context  = new AnnotationConfigApplicationContext(TestConfiguration.class);
    UserService service = context.getBean(UserService.class);

    service.login("bro", "bro");
    service.login("goku", "goku");
    service.login("gohan", "gohan");
  }
}
