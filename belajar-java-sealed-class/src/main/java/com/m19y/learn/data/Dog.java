package com.m19y.learn.data;

public record Dog() implements SayHello {
  @Override
  public String hello() {
    return "rawr";
  }
}
