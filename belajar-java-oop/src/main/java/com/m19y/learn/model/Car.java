package com.m19y.learn.model;

public interface Car extends HasBrand {
  public abstract void drive(); // by default interface akan mengubah seluruh method menjadi public abstract
  int getTire();
  default boolean isBig(){
    return false;
  }
}
