package com.m19y.learn.helper;

import org.springframework.stereotype.Component;

@Component
public class StringHelper {

  public boolean isPalindrome(String value){
    String reverse = new StringBuilder(value).reverse().toString();
    return reverse.equals(value);
  }
}
