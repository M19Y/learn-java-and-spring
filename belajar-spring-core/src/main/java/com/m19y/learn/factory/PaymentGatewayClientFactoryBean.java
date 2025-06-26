package com.m19y.learn.factory;

import com.m19y.learn.client.PaymentGatewayClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("paymentGatewayClient")
public class PaymentGatewayClientFactoryBean implements FactoryBean<PaymentGatewayClient> {

  @Override
  public PaymentGatewayClient getObject() throws Exception {
    PaymentGatewayClient paymentGatewayClient = new PaymentGatewayClient();
    paymentGatewayClient.setEndpoint("https://example.com");
    paymentGatewayClient.setPublicKey("public");
    paymentGatewayClient.setPrivateKey("private");
    return paymentGatewayClient;
  }

  @Override
  public Class<?> getObjectType() {
    return PaymentGatewayClient.class;
  }
}
