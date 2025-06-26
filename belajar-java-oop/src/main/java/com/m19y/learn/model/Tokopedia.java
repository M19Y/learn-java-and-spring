package com.m19y.learn.model;

public class Tokopedia {

  public void login(LoginRequest request){
    System.out.println(request.username());
    System.out.println(request.password());
  }
}
