package com.m19y.practice;

public class ThreadCommunication {

  public static void main(String[] args) {
    String message = null;

    Thread thread1 = new Thread(()-> {
      // ini bahaya
      while(message == null){
        // will wait until the world end
      }
      System.out.println(message);
    });

    thread1.start();
  }
}
