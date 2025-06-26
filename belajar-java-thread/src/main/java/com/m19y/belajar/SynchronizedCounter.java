package com.m19y.belajar;

public class SynchronizedCounter {

  private Long value = 0L;

  public synchronized void syncMethodIncrement(){
    // terlock secara keseluruhan (monitor lock)
    System.out.println(Thread.currentThread().getState() + " : " + Thread.currentThread().getName());
    value++;
  }

  public void syncStatementIncrement(){
    // ini tidak terkucin
    System.out.println(Thread.currentThread().getState() + " : " + Thread.currentThread().getName());

    // ini saja yang terkunci
    synchronized (this){
    value++;
    }

    // apabila ada kode dibawah, maka kodenya juga tidak terkunci
  }

  public Long getValue(){
    return value;
  }
}
