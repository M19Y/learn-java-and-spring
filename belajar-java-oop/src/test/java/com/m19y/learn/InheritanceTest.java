package com.m19y.learn;

import com.m19y.learn.model.Manager;
import com.m19y.learn.model.VicePresident;
import org.junit.jupiter.api.Test;

public class InheritanceTest {

  @Test
  void create() {

    Manager manager = new Manager();
    manager.name = "Ucup";
    manager.sayHallo("Bro");

    VicePresident vp = new VicePresident();
    vp.name = "Jama";
    vp.sayHallo("Nani");


  }
}
