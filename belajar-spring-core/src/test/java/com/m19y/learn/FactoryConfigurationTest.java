package com.m19y.learn;

import com.m19y.learn.client.PaymentGatewayClient;
import com.m19y.learn.factory.PaymentGatewayClientFactoryBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class FactoryConfigurationTest {

  @Test
  void testPaymentGateway() throws Exception {

    ApplicationContext context = new AnnotationConfigApplicationContext(FactoryConfiguration.class);

    PaymentGatewayClient payment =
            context.getBean(PaymentGatewayClient.class);

    Assertions.assertEquals("public", payment.getPublicKey());
    Assertions.assertEquals("private", payment.getPrivateKey());
    Assertions.assertEquals("https://example.com", payment.getEndpoint());
  }
}