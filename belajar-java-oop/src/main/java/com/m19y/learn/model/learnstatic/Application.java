package com.m19y.learn.model.learnstatic;

public class Application {

  public final static int PROCESSORS;

  static {
    System.out.println("Call this first");
    PROCESSORS = Runtime.getRuntime().availableProcessors();
  }
}
