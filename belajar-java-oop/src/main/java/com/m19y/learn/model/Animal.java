package com.m19y.learn.model;

public abstract class Animal {

  public String name;
  // tidak boleh method di abstract itu private
  public abstract void run();

  // kita bisa membuat method kongkrit yang bisa langsung dipakai di subclass
  // tanpa harus di implement oleh subclass
  public void sound(String sound){
    System.out.println(name + " says " + sound );
  }
}
