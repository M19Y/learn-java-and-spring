package com.m19y.learn.client;

import lombok.Data;

@Data
public class PaymentGatewayClient {

  private String endpoint;
  private String privateKey;
  private String publicKey;
}
