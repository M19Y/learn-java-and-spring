package com.m19y.learn;

import com.m19y.learn.data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ConstructorTest {

  @Test
  void testGetConstructorFromAClass() {
    Class<Person> personClass = Person.class;
    Constructor<?>[] constructors = personClass.getConstructors();

    for (Constructor<?> constructor : constructors) {
      System.out.println("==========");
      System.out.println(constructor.getName());
      System.out.println(Arrays.toString(constructor.getParameterTypes()));
      System.out.println("==========\n");
    }
  }

  @Test
  void testCreateNewInstanceFromConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Class<Person> personClass = Person.class;

    Constructor<Person> defaultConstructor = personClass.getConstructor();
    Constructor<Person> constructorParameters = personClass.getConstructor(String.class, String.class);

    // cara biasa
    Person person = new Person("Elon", "Musk");

    // menggunakan getConstructor dari Constructor<Person>
    Person personNewInstance = constructorParameters.newInstance("James", "Gosling");

    System.out.println(defaultConstructor);
    System.out.println(constructorParameters);
    Assertions.assertEquals("James", personNewInstance.getFirstname());
    Assertions.assertNotNull(personNewInstance);
    Assertions.assertNotNull(person);

  }
}
