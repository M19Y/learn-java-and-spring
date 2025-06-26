package com.m19y.learn.model;

import com.m19y.learn.Facebook;

// will be error, compile error because Facebook is final
// public class FakeFacebook extends Facebook {
public class FakeFacebook {

  public final void login(String name, String password){
    System.out.println(name + " : " + password);
  }
}
