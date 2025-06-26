package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class MethodValidation09Test extends AbstractValidatorTest {

  @Test
  void testSayHelloNotBlank() throws NoSuchMethodException {

    Person person = new Person();
    String name = "";

    // get sayHello method from person
    Method sayHelloMethod = Person.class.getMethod("sayHello", String.class);

    // get all parameter inside sayHello method that contains constraint
    Set<ConstraintViolation<Person>> violations = executableValidator
            .validateParameters(person, sayHelloMethod, new Object[]{name});

    for (ConstraintViolation<Person> violation : violations) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println("======\n");
    }

    Assertions.assertEquals("name can not blank", violations.stream().findFirst().get().getMessage());

  }

  @Test
  void testSayHelloNotBlankSuccess() throws NoSuchMethodException {

    Person person = new Person();
    String name = "bro";

    // get sayHello method from person
    Method sayHelloMethod = Person.class.getMethod("sayHello", String.class);

    // get all parameter inside sayHello method that contains constraint
    Set<ConstraintViolation<Person>> violations = executableValidator
            .validateParameters(person, sayHelloMethod, new Object[]{name});

    // empty
    for (ConstraintViolation<Person> violation : violations) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println("======\n");
    }
  }

  @Test
  void testFullNameReturnValueNotBlank() throws NoSuchMethodException {
    Person person = new Person();
    person.setFirstname("");
    person.setLastname("");

    String returnValue = person.fullName();

    Method fullNameMethod = Person.class.getMethod("fullName");

    Set<ConstraintViolation<Person>> violations = executableValidator.validateReturnValue(person, fullNameMethod, returnValue);
    for (ConstraintViolation<Person> violation : violations) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println("======\n");
    }

    Set<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
    Assertions.assertTrue(errorMessages.contains("full name can not blank"));
    Assertions.assertEquals("", returnValue.trim());

  }

  @Test
  void testFullNameReturnValueSuccess() throws NoSuchMethodException {
    Person person = new Person();
    person.setFirstname("Bro");
    person.setLastname("");

    String returnValue = person.fullName();

    Method fullNameMethod = Person.class.getMethod("fullName");

    Set<ConstraintViolation<Person>> violations = executableValidator.validateReturnValue(person, fullNameMethod, returnValue);
    Set<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
    Assertions.assertFalse(errorMessages.contains("full name can not blank"));
    Assertions.assertEquals("Bro", returnValue.trim());

  }
}
