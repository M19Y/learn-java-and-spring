package com.m19y.learn;

import lombok.Synchronized;

public class Counter {

  private final Object counterLock = new Object();

  private Long counter = 0L;

  @Synchronized("counterLock")
  public void increment(){
    counter = counter + 1;
  }

  @Synchronized("counterLock")
  public Long getCounter() {
    return counter;
  }

}
