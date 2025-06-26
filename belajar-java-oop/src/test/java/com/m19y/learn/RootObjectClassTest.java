package com.m19y.learn;

import com.m19y.learn.model.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RootObjectClassTest {

  @Test
  void create() {
    Manager manager = new Manager();

    manager.name = "Bro";
    Assertions.assertSame(manager.name, manager.name.toString());
  }
}
