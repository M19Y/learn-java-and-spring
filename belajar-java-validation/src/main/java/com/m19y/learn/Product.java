package com.m19y.learn;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class Product {

  // harus di definiskan didalam container (list, set, ect)
  private List<@NotBlank(message = "product ingredients must not blank") String> ingredients;

  // tidak harus di definisikan didalamnya jika type nya adalah object
  @Valid
  @NotNull(message = "order must not null")
  private List<Order> orders;

  public List<String> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }
}
