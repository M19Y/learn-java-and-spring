package com.m19y.learn.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReflectionTest {

  @Test
  void reflection() {

    Assertions.assertTrue(SayHello.class.isSealed());
    Class<?>[] permittedSubclasses = SayHello.class.getPermittedSubclasses();
    Assertions.assertEquals(3, permittedSubclasses.length);
    Assertions.assertEquals(Human.class, permittedSubclasses[0]);
    Assertions.assertEquals(Dog.class, permittedSubclasses[1]);
    Assertions.assertEquals(Cat.class, permittedSubclasses[2]);
  }
}
