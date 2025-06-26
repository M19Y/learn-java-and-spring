package com.m19y.learn;

import com.m19y.learn.data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterTest {

  @Test
  void testGetParameter() {

    Class<Person> personClass = Person.class;
    Method[] methods = personClass.getDeclaredMethods();

    for (Method method : methods) {
      System.out.println("======");
      System.out.println(method.getName());
      Parameter[] parameters = method.getParameters();
      for (Parameter parameter : parameters) {
        System.out.println(parameter.getType().getSimpleName() + " : " +parameter.getName());
      }
      System.out.println("======\n");
    }
  }

  @Test
  void testInvokeMethodWithParameters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Class<Person> personClass = Person.class;
    Method setLastname = personClass.getDeclaredMethod("setLastname", String.class);

    Person person = new Person();
    setLastname.invoke(person, "Musk"); // like -> person.setLastname("Musk");

    Assertions.assertEquals("Musk", person.getLastname());
  }
}
