package com.m19y.learn.model;

public class Cat extends Animal{

  public void run() {
    System.out.println("Cat " + this.name + " is running");
  }

  @Override
  public void sound(String sound) {
    System.out.println(this.name +  " Cat says " + sound);
  }
}
