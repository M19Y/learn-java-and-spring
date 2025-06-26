package com.m19y.learn;

import com.m19y.learn.data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ClassTest {

  @Test
  void testCreate() throws ClassNotFoundException {

    // ada 3 cara membuat Class (java.lang)

    // 1. dengan menggunakan NamaClass.class
    Class<Person> personClass1 = Person.class;

    // 2. dengan Class.forName("location.package.class")
    Class<?> personClass2 = Class.forName("com.m19y.learn.data.Person");

    // 3. dengan instansiasi dari object.GetClass();
    Person person = new Person();
    Class<? extends Person> aClassPerson = person.getClass();

    Assertions.assertInstanceOf(Class.class, aClassPerson);
    Assertions.assertSame(personClass1, personClass2);
    Assertions.assertThrows(ClassNotFoundException.class,
            () -> Class.forName("com.m19y.learn.data.Wrong"));
  }

  @Test
  void testClassMethods() {

    Class<Person> personClass = Person.class;

    Assertions.assertSame(Object.class, personClass.getSuperclass()); // class java.lang.Object
    System.out.println(personClass.getSuperclass());

    Assertions.assertEquals(0, personClass.getInterfaces().length); // []
    System.out.println(Arrays.toString(personClass.getInterfaces()));

    Assertions.assertEquals(2, personClass.getDeclaredConstructors().length); // (), (firstName, lastName)
    System.out.println(Arrays.toString(personClass.getDeclaredConstructors()));

    Assertions.assertEquals(5, personClass.getDeclaredMethods().length); // toString, getter and setter
    System.out.println(Arrays.toString(personClass.getDeclaredMethods()));

    Assertions.assertEquals(2, personClass.getDeclaredFields().length); // firstname, lastname
    System.out.println(Arrays.toString(personClass.getDeclaredFields()));

    Assertions.assertEquals(1, personClass.getModifiers()); // public -> 1
    System.out.println(personClass.getModifiers());

    System.out.println(personClass.getPackage());
    System.out.println(personClass.getPackageName());

    Assertions.assertEquals("Person", personClass.getSimpleName());
    System.out.println(personClass.getSimpleName());
  }
}
