package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class SuperConstructorTest {

  @Test
  void create() {

    MangerSuper manger = new MangerSuper("Bro");
    VicePresidentSuper vp = new VicePresidentSuper("Sis");

    System.out.println(manger.name);
    System.out.println(vp.name);

    manger.sayHallo("Uncle");
    vp.sayHallo("Aunty");

  }
}
