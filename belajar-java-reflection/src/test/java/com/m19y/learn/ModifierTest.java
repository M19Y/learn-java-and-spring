package com.m19y.learn;

import com.m19y.learn.data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

public class ModifierTest {


  @Test
  void testGetModifier() {

    Class<Person> personClass = Person.class;

    System.out.println(personClass.getModifiers());
    Assertions.assertTrue(Modifier.isPublic(personClass.getModifiers()));
    Assertions.assertFalse(Modifier.isFinal(personClass.getModifiers()));
    Assertions.assertFalse(Modifier.isStatic(personClass.getModifiers()));

  }

  private static final class Bro{
    // test class that have multiple modifiers
  }

  @Test
  void testMultipleModifiers() {
    Class<Bro> broClass = Bro.class;
    System.out.println(broClass.getModifiers());
    Assertions.assertTrue(Modifier.isStatic(broClass.getModifiers()));
    Assertions.assertTrue(Modifier.isPrivate(broClass.getModifiers()));
    Assertions.assertTrue(Modifier.isFinal(broClass.getModifiers()));
  }
}
