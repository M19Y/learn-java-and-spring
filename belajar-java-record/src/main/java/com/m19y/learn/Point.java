package com.m19y.learn;

import com.m19y.learn.annotation.Valid;

/*
* Kita bisa menambahkan annotation pada parameter di record*/
public record Point(@Valid int x, @Valid int y) {

  // compact constructor
  public Point{
    System.out.println("Create Point");
  }

  // static field and method
  public static final Point ZERO = new Point(0, 0);

  public static Point create(int x, int y){
    return new Point(x, y);
  }
}
