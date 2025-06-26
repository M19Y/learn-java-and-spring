package com.m19y.learn;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Validator01Test {

  @Test
  void testCreateValidator() {

    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    Assertions.assertNotNull(validator);

    validatorFactory.close();
  }
}
