package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstructorValidation10Test extends AbstractValidatorTest {


  @Test
  void testDefaultConstructorEmpty() throws NoSuchMethodException {

    String firstname = "";
    String lastname = "";
    Address address = null;
    Constructor<Person> constructor = Person.class.getConstructor(String.class, String.class, Address.class);
    Set<ConstraintViolation<Person>> violations = executableValidator
            .validateConstructorParameters(constructor, new Object[]{firstname, lastname, address});


    Set<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

    for (ConstraintViolation<Person> violation : violations) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println("======\n");
    }

    Assertions.assertTrue(errorMessages.contains("firstname can not blank"));
    Assertions.assertTrue(errorMessages.contains("lastname can not blank"));
    Assertions.assertTrue(errorMessages.contains("address can not null"));
  }

  @Test
  void testDefaultConstructorEmptyNested() throws NoSuchMethodException {

    String firstname = "";
    String lastname = "";
    Address address = new Address();
    Constructor<Person> constructor = Person.class.getConstructor(String.class, String.class, Address.class);
    Set<ConstraintViolation<Person>> violations = executableValidator
            .validateConstructorParameters(constructor, new Object[]{firstname, lastname, address});


    Set<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

    for (ConstraintViolation<Person> violation : violations) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println("======\n");
    }

    // person
    Assertions.assertTrue(errorMessages.contains("firstname can not blank"));
    Assertions.assertTrue(errorMessages.contains("lastname can not blank"));
    Assertions.assertFalse(errorMessages.contains("address can not null"));

    // address (nested)
    Assertions.assertTrue(errorMessages.contains("country can not be blank"));
    Assertions.assertTrue(errorMessages.contains("city can not be blank"));
    Assertions.assertTrue(errorMessages.contains("street can not be blank"));
  }

  @Test
  void testDefaultConstructorReturnValueEmpty() throws NoSuchMethodException {

    Person person = new Person("", "", null);
    Constructor<Person> constructor = Person.class.getConstructor(String.class, String.class, Address.class);

    // violation ini akan memvalidasi field, bukan pada constructor parameter)
    Set<ConstraintViolation<Person>> violations = executableValidator
            .validateConstructorReturnValue(constructor, person);


    Set<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

    for (ConstraintViolation<Person> violation : violations) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println("======\n");
    }

    // person
    Assertions.assertTrue(errorMessages.contains("firstname must be not blank"));
    Assertions.assertTrue(errorMessages.contains("lastname must be not blank"));
    Assertions.assertTrue(errorMessages.contains("address can not be null"));

  }
}
