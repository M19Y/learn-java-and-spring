package com.m19y.learn.model;

public class Rectangle extends Shape {

  // overriding parent
  public int getCorner() {
    return 4;
  }

  public int getParentCorner(){
    return super.getCorner();
  }
}
