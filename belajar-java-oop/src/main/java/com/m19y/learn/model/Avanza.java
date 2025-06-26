package com.m19y.learn.model;

public class Avanza implements Car, IsMaintenance{

  @Override
  public void drive() {
    System.out.println("Drive Avanza");
  }

  @Override
  public int getTire() {
    return 4;
  }

  @Override
  public String getBrand() {
    return "Toyota";
  }

  @Override
  public boolean isMaintenance() {
    return false;
  }
}
