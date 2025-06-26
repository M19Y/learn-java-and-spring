package com.m19y.learn.util;

import jakarta.validation.*;
import jakarta.validation.executable.ExecutableValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;

public abstract class AbstractValidatorTest {
  protected ValidatorFactory validatorFactory;
  protected Validator validator;
  protected ExecutableValidator executableValidator;
  protected MessageInterpolator messageInterpolator;

  @BeforeEach
  void setUp() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
    executableValidator = validator.forExecutables();
    messageInterpolator = validatorFactory.getMessageInterpolator();
  }

  @AfterEach
  void tearDown() {
    validatorFactory.close();
  }

  protected void viewViolation(Object o){
    Set<ConstraintViolation<Object>> violations = validator.validate(o);
    for (ConstraintViolation<Object> violation : violations) {
      System.out.println("\nMessage: " + violation.getMessage());
      System.out.println("Bean: " + violation.getLeafBean());
      System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
      System.out.println("Invalid value: " + violation.getInvalidValue());
      System.out.println("Path: "+ violation.getPropertyPath());
      System.out.println("==========");
    }
  }

  protected void validationWithThrowException(Object o){
    Set<ConstraintViolation<Object>> violations = validator.validate(o);
    if(!violations.isEmpty()){
      throw new ConstraintViolationException(violations);
    }
  }

  protected void viewViolationWithGroup(Object o, Class<?> ... groups){
    Set<ConstraintViolation<Object>> violations = validator.validate(o, groups);
    for (ConstraintViolation<Object> violation : violations) {
      System.out.println("\nMessage: " + violation.getMessage());
      System.out.println("Bean: " + violation.getLeafBean());
      System.out.println("Constraint: " + violation.getConstraintDescriptor().getAnnotation());
      System.out.println("Invalid value: " + violation.getInvalidValue());
      System.out.println("Path: "+ violation.getPropertyPath());
      System.out.println("==========");
    }
  }
}
