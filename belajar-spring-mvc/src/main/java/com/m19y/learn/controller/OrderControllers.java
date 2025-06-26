package com.m19y.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderControllers {

  @ResponseBody
  @GetMapping(path = "orders/{orderId}/products/{productId}")
  public String order(@PathVariable(name = "orderId") String orderId,
                      @PathVariable(name = "productId") Integer productId){
    return """
            Order : $orderId, Product $productId
            """.replace("$orderId", orderId)
            .replace("$productId", String.valueOf(productId));
  }
}
