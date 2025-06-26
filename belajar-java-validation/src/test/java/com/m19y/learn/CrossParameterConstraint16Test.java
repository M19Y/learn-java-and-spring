package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class CrossParameterConstraint16Test extends AbstractValidatorTest {

  @Test
  void testCrossParameterInsideMethodError() throws NoSuchMethodException {

    Method method = UserService.class.getMethod("register", String.class, String.class, String.class);
    String username = "bro";
    String password = "secret";
    String retypePassword = "secret123";

    Set<ConstraintViolation<UserService>> constraintViolations = executableValidator.validateParameters(
            new UserService(),
            method,
            new Object[]{username, password, retypePassword}
    );

    Set<String> messages = constraintViolations.stream()
            .map(ConstraintViolation::getMessage).collect(Collectors.toSet());

    Assertions.assertTrue(messages.contains("password and retype password must same"));

    constraintViolations.stream()
            .peek(err -> System.out.println(err.getPropertyPath()))
            .map(ConstraintViolation::getMessage)
            .forEach(System.out::println);
  }
}

