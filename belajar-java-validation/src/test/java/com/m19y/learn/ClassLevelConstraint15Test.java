package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

public class ClassLevelConstraint15Test extends AbstractValidatorTest {

  @Test
  void testPasswordAndRetypePasswordNotSame() {

    Register register = new Register();
    register.setUsername("bro");
    register.setPassword("secret123");
    register.setRetypePassword("secret");

    Set<String> messages = validator.validate(register).stream()
            .peek(violation -> System.out.println(violation.getPropertyPath()))
            .map(ConstraintViolation::getMessage)
            .peek(System.out::println)
            .collect(Collectors.toSet());

    // karena kita membuat validasi pada level class maka propertyPathnya kosong
    Assertions.assertTrue(messages.contains("password and retype password must same"));

  }

  @Test
  void testPasswordAndRetypePasswordAreSame() {

    Register register = new Register();
    register.setUsername("bro");
    register.setPassword("secret123");
    register.setRetypePassword("secret123");

    Assertions.assertEquals(0, validator.validate(register).size());
  }
}
