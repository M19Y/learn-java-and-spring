package com.m19y.learn;

import com.m19y.learn.data.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrimitiveTypeTest {

  @Test
  void testClass() {

    Class<Integer> intPrimitive = int.class;
    Class<Boolean> booleanPrimitive = boolean.class;
    Class<Integer> integerNonPrimitive = Integer.class;

    Assertions.assertTrue(intPrimitive.isPrimitive());
    Assertions.assertTrue(booleanPrimitive.isPrimitive());
    Assertions.assertFalse(integerNonPrimitive.isPrimitive());

  }

  @Test
  void testField() throws NoSuchFieldException, IllegalAccessException {
    Class<Animal> animalClass = Animal.class;
    Field age = animalClass.getDeclaredField("age");

    Assertions.assertTrue(age.getType().isPrimitive());

    // to get a data result from an object that has primitive type init
    Animal animal = new Animal("Frog", 2);
    age.setAccessible(true);
    int frogAge = age.getInt(animal);

    Assertions.assertEquals(animal.getAge(), frogAge);
  }

  @Test
  void testInvokeMethodWithPrimitiveParam() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    Class<Animal> animalClass = Animal.class;
    Method setAge = animalClass.getMethod("setAge", int.class);

    Animal animal = new Animal();
    setAge.invoke(animal, 12);

    Assertions.assertEquals(12, animal.getAge());
  }
}
