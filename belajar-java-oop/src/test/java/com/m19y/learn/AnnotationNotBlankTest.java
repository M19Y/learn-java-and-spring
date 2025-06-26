package com.m19y.learn;

import com.m19y.learn.annotation.CreateUserRequest;
import com.m19y.learn.annotation.ValidationReflection;
import com.m19y.learn.model.exception.BlankException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnnotationNotBlankTest {

  @Test
  void blankUsernameAndPassword() {

    CreateUserRequest request = new CreateUserRequest();

    BlankException exception = Assertions.assertThrows(BlankException.class, () -> ValidationReflection.validate(request));
    Assertions.assertEquals("Field username must be not null or blank", exception.getMessage());

  }

  @Test
  void blankPassword() {

    CreateUserRequest request = new CreateUserRequest();
    request.setUsername("m19y");
    BlankException exception = Assertions.assertThrows(BlankException.class, () -> ValidationReflection.validate(request));
    Assertions.assertEquals("Field password must be not null or blank", exception.getMessage());

  }

  @Test
  void success() {

    CreateUserRequest request = new CreateUserRequest();
    request.setUsername("m19y");
    request.setPassword("simple123");
    Assertions.assertDoesNotThrow(() -> ValidationReflection.validate(request));

  }
}
