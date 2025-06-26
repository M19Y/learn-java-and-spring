package com.m19y.learn;

import com.m19y.learn.data.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class TypeVariableTest {

  @Test
  void testGetTypeVariables() {

    Class<Data> dataClass = Data.class;

    TypeVariable<Class<Data>>[] typeParameters = dataClass.getTypeParameters();

    for (TypeVariable<Class<Data>> variable : typeParameters) {
      System.out.println(variable);
      System.out.println(variable.getName());
      System.out.println(Arrays.toString(variable.getBounds()));

    }

  }
}
