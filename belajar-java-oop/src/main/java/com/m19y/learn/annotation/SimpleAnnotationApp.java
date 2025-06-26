package com.m19y.learn.annotation;

@Fancy(name = "simple-app", tags = {"simple", "app"})
public class SimpleAnnotationApp {

//  @Fancy(name = "simple-method") // not applicable, karena kita mengeset ElementType dari interface adalah Type
  public static void mySimpleMethod(){
    System.out.println("This is a simple method");
  }
}
