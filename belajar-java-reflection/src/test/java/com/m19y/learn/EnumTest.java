package com.m19y.learn;

import com.m19y.learn.data.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EnumTest {

  @Test
  void enumTest() {

    Class<Gender> genderClass = Gender.class;

    Assertions.assertTrue(genderClass.isEnum());
    System.out.println(genderClass.getSuperclass());
    System.out.println(Arrays.toString(genderClass.getDeclaredConstructors()));
    System.out.println(Arrays.toString(genderClass.getEnumConstants()));
    System.out.println(Arrays.toString(genderClass.getDeclaredFields()));
    System.out.println(Arrays.toString(genderClass.getDeclaredMethods()));
  }
}
