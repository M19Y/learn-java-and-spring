package com.m19y.learn.model;

// bisa mengimplement, tapi tidak bisa melakukan extends
public record LoginRequest(String username, String password) implements StringUtils{

  public LoginRequest{
    System.out.println("Constructor utama");
  }

  public LoginRequest(String username){
    this(username, null);
  }

  @Override
  public String toUpper() {
    return username.toUpperCase();
  }

  @Override
  public Integer getLength() {
    return username.length();
  }
}
