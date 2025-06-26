package com.m19y.learn.data;

public record Cat() implements SayHello {
  @Override
  public String hello() {
    return "meow";
  }
}
