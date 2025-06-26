package com.m19y.learn;

import com.m19y.learn.model.polymorphism.Child;
import com.m19y.learn.model.polymorphism.Parent;
import org.junit.jupiter.api.Test;

public class VariableHidingTest {


  @Test
  void create() {

    Child child = new Child();
    child.name = "Bro";
    child.doIt();
    System.out.println(child.name);

    Parent parent = (Parent) child;
    parent.doIt();
    System.out.println(parent.name);
  }

}
