package com.m19y.learn;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Order {

  @NotBlank(message = "order id must not null")
  private String id;

  @Min(value = 1, message = "order total must be at least {value}")
  private int total;

}
