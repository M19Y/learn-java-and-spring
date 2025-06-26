package com.m19y.learn.model.learnstatic;

public class MathUtil {

  public static int sum(int... values){
    int total = 0;
    for (int value: values){
      total += value;
    }
    return total;
  }
}
