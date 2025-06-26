package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

public class ConstraintValidatorContextParameter17Test extends AbstractValidatorTest {

  @Test
  void testPasswordAndRetypePasswordNotSame() {

    Register register = new Register();
    register.setUsername("bro");
    register.setPassword("secret123");
    register.setRetypePassword("secret");

    Set<String> messages = validator.validate(register).stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toSet());

    Set<String> propertyPath = validator.validate(register).stream()
            .map(violation -> violation.getPropertyPath().toString())
            .collect(Collectors.toSet());

    // karena kita membuat validasi pada level class maka propertyPathnya kosong
    Assertions.assertTrue(propertyPath.contains("password"));
    Assertions.assertTrue(messages.contains("password and retype password must same"));

    Assertions.assertTrue(propertyPath.contains("retype password"));
    Assertions.assertTrue(messages.contains("retype password is different with password"));

  }
}
