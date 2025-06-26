package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayTest {

  @Test
  void primitiveArrayTest() {

    Class<String[]> aStringArray = String[].class;
    Class<int[]> aArrayClass = int[].class;

    Assertions.assertFalse(aArrayClass.isPrimitive());
    Assertions.assertFalse(aStringArray.isPrimitive());

    Assertions.assertTrue(aArrayClass.isArray());
    Assertions.assertTrue(aStringArray.isArray());

    Assertions.assertEquals(0, aArrayClass.getDeclaredConstructors().length);
    Assertions.assertEquals(0, aArrayClass.getDeclaredFields().length);
    Assertions.assertEquals(0, aArrayClass.getDeclaredMethods().length);

    // type
    Assertions.assertEquals(String.class, aStringArray.getComponentType());

  }

  @Test
  void arrayManipulationTest() {

    Class<String[]> aStringArrayClass = String[].class;

    String[] strArray = (String[]) Array.newInstance(String.class, 10);

    System.out.println(Arrays.toString(strArray));

    // manipulation
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Array.set(strArray, 0, 1); // wrong value
    });

    Array.set(strArray, 0, "Bro");
    Array.set(strArray, 1, "Sis");
    Array.set(strArray, 2, "Unc");

    Assertions.assertEquals("Bro", strArray[0]);
    Assertions.assertEquals("Sis", strArray[1]);
    Assertions.assertEquals("Unc", strArray[2]);

  }
}
