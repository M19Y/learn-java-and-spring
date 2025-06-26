package com.m19y.learn;

import com.m19y.learn.data.Connection;
import com.m19y.learn.data.Server;
import com.m19y.learn.data.Server2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class LifecycleConfigurationTest {

  ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(LifecycleConfiguration.class);
    context.registerShutdownHook();
  }

  @AfterEach
  void tearDown() {
//    context.close();
  }

  @Test
  void testLifecycleUsingInitialBeanAndDisposableBean() {
    Connection connection = context.getBean(Connection.class);
  }

  @Test
  void testLifecycleUsingBeanAnnotationMethod() {
    Server server = context.getBean(Server.class);
  }

  @Test
  void testLifecycleUsingAnnotationPreAndPost() {
    Server2 server2 = context.getBean(Server2.class);
  }
}
