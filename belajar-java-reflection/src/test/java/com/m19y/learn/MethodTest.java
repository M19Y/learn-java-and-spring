package com.m19y.learn;

import com.m19y.learn.data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodTest {

  @Test
  void testGetMethods() {

    Class<Person> personClass = Person.class;

    Method[] declaredMethods = personClass.getDeclaredMethods();
    for (Method method : declaredMethods) {
      System.out.println("===========");
      System.out.println(method.getName());
      System.out.println(method.getGenericReturnType());
      System.out.println(Arrays.toString(method.getParameters()));
      System.out.println(Arrays.toString(method.getExceptionTypes()));
      System.out.println("===========\n");
    }
  }

  @Test
  void testInvokeMethodValueWithoutParameter() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Class<Person> personClass = Person.class;
    Method getFirstname = personClass.getDeclaredMethod("getFirstname");

    Person person = new Person("Elon", "Musk");

    // memanggil method menggukan invooke
    String firstname = (String) getFirstname.invoke(person); // like  -> person.getFirstname()
    Assertions.assertEquals(person.getFirstname(), firstname);
  }
}
