package com.m19y.learn;

import com.m19y.learn.data.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class ValidatorTest {

  @Autowired
  private Validator validator;

  @Test
  void testValidatorForPersonFailed() {
    Person person = new Person("", "");
    Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

    Assertions.assertFalse(constraintViolations.isEmpty());
    Assertions.assertEquals(2, constraintViolations.size());

  }

  @Test
  void testValidatorForPersonSuccess() {
    Person person = new Person("1", "Son Goku");
    Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

    Assertions.assertTrue(constraintViolations.isEmpty());

  }
}
