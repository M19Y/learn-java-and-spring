package com.m19y.belajar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {


  private static final Logger log = LoggerFactory.getLogger(CounterReadWriteLock.class);
  private Long value = 0L;
  private final ReadWriteLock lock = new ReentrantReadWriteLock();
  public void increment(){
    try{
      lock.writeLock().lock();
      value++;
    }finally {
      lock.writeLock().unlock();
    }
  }

  public Long getValue() {
    try{
      lock.readLock().lock();
      return value;
    }finally {
      lock.readLock().unlock();
    }
  }
}
