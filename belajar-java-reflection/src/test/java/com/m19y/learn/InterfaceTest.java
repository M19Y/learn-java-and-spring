package com.m19y.learn;

import com.m19y.learn.data.Employee;
import com.m19y.learn.data.Manager;
import com.m19y.learn.data.Nameable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class InterfaceTest {

  @Test
  void testGetInterface() {

    Class<Nameable> nameableClass = Nameable.class;


    Assertions.assertTrue(nameableClass.isInterface());
    Assertions.assertEquals("com.m19y.learn.data.Nameable", nameableClass.getName());
    Assertions.assertNull(nameableClass.getSuperclass());
    Assertions.assertEquals(0, nameableClass.getInterfaces().length); // []
    Assertions.assertEquals(0, nameableClass.getDeclaredFields().length); // []
    Assertions.assertEquals(2, nameableClass.getDeclaredMethods().length); // [getFirstname, getLastname]
    Assertions.assertEquals(0, nameableClass.getDeclaredConstructors().length); // []

    System.out.println(nameableClass.isInterface());
    System.out.println(nameableClass.getName());
    System.out.println(nameableClass.getSuperclass());
    System.out.println(Arrays.toString(nameableClass.getInterfaces()));
    System.out.println(Arrays.toString(nameableClass.getDeclaredFields()));
    System.out.println(Arrays.toString(nameableClass.getDeclaredMethods()));
    System.out.println(Arrays.toString(nameableClass.getDeclaredConstructors()));
  }

  @Test
  void getSuperInterface() {

    Class<Employee> employee = Employee.class;
    System.out.println(Arrays.toString(employee.getInterfaces()));
    Assertions.assertEquals(1, employee.getInterfaces().length);
  }
}
