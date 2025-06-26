package com.m19y.learn.data;

public record Human() implements SayHello {
  @Override
  public String hello() {
    return "helo";
  }
}
