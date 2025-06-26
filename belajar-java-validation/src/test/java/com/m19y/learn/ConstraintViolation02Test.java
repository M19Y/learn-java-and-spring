package com.m19y.learn;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.*;

import java.util.Set;

public class ConstraintViolation02Test {

  private ValidatorFactory validatorFactory;
  private Validator validator;

  @BeforeEach
  void setUp() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterEach
  void tearDown() {
    validatorFactory.close();
  }

  @Test
  void testValidationFailedOnNotBlankConstraint() {
    Person person = new Person();

    Set<ConstraintViolation<Person>> violations = validator.validate(person);

    Assertions.assertEquals(3, violations.size());
  }

  @Test
  @DisplayName("Error constraint size because the firstname and lastname reached max value")
  void testValidationFailedOnSizeConstraint() {
    Person person = new Person();
    person.setFirstname("aaaaaaaaaaaaaAAAAAAAAAAAAAAAAAaaaaaaaaaaaaaaa");
    person.setLastname("aaaaaaaaaaaaaAAAAAAAAAAAAAAAAAaaaaaaaaaaaaaaa");

    Set<ConstraintViolation<Person>> violations = validator.validate(person);

    for (ConstraintViolation<Person> violation : violations) {
      System.out.println("Message: " + violation.getMessage());
      System.out.println("Bean: " + violation.getLeafBean());
      System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
      System.out.println("Invalid value: " + violation.getInvalidValue());
      System.out.println("Path: "+ violation.getPropertyPath());
      System.out.println("==========\n");
    }

    Assertions.assertEquals(2, violations.size());
  }

  @Test
  void validationSuccess() {

    Person person = new Person("Elon", "Musk");

    Set<ConstraintViolation<Person>> violations = validator.validate(person);

    Assertions.assertEquals(0, violations.size());
  }
}
