package com.m19y.learn;

public class Product {

  public String name;
  public int price;

  public Product(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString(){
    return String.format("Product => Name: %s, Price: Rp%s", name, price);
  }
}
