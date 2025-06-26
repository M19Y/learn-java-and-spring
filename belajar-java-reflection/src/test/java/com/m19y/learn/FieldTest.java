package com.m19y.learn;

import com.m19y.learn.data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class FieldTest {

  @Test
  void testGetFields() {
    Class<Person> personClass = Person.class;

    Field[] declaredFields = personClass.getDeclaredFields();

    for (Field filed : declaredFields) {
      System.out.println(filed.getName() + " : " + filed.getType().getSimpleName());
    }
  }

  @Test
  void getFieldValue() throws NoSuchFieldException, IllegalAccessException {

    Class<Person> personClass = Person.class;
    Field firstname = personClass.getDeclaredField("firstname");
    Person person = new Person("Elon", "Musk");

    // akan error apabila kita tidak mengubah accessiblenya menjadi true
    // karena filed dari firstname memiliki access modifier 'private'
    Assertions.assertThrows(IllegalAccessException.class, () -> {
      firstname.get(person);
    });

    // mengubah access modifier dari field firsname
    firstname.setAccessible(true);
    String personFirstname = (String) firstname.get(person);

    Assertions.assertEquals(person.getFirstname(), personFirstname);

    Person person2 = new Person("James", "Gosling");

    // kita tidak perlu memgubah accessible nya menjadi true lagi
    // karena cukup sekali saja
    String person2Firstname = (String) firstname.get(person2);

    Assertions.assertEquals(person2.getFirstname(), person2Firstname);
  }

  @Test
  void testSetFieldValue() throws NoSuchFieldException, IllegalAccessException {

    Class<Person> personClass = Person.class;

    // mengakses filed yang tidak ada
    Assertions.assertThrows(NoSuchFieldException.class, () -> personClass.getDeclaredField("not exists filed"));

    Field lastname = personClass.getDeclaredField("lastname");
    lastname.setAccessible(true);

    Person person = new Person("Elon", "Musk");

    // set the lastname from Musk to X
    lastname.set(person, "X"); // like -> peson.setLastname("X")

    // error jika salah memasukan tipe data
    Assertions.assertThrows(IllegalArgumentException.class, () -> lastname.set(person, true));

    String elonLastname = (String) lastname.get(person);

    Assertions.assertEquals("X", elonLastname);

    // reflection langsung mengubah reference objectnya
    Assertions.assertEquals(person.getLastname(), elonLastname);

  }
}
