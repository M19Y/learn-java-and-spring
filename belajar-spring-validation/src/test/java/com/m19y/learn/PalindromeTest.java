package com.m19y.learn;

import com.m19y.learn.data.Foo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class PalindromeTest {

  @Autowired
  private Validator validator;

  @Test
  void palindromeValid() {
    Foo foo = new Foo("kodok");
    Set<ConstraintViolation<Foo>> constraintViolations = validator.validate(foo);
    Assertions.assertTrue(constraintViolations.isEmpty());

  }

  @Test
  void palindromeInvalid() {
    Foo foo = new Foo("bro");
    Set<ConstraintViolation<Foo>> constraintViolations = validator.validate(foo);
    Assertions.assertFalse(constraintViolations.isEmpty());
    Assertions.assertEquals(1, constraintViolations.size());
  }

  @Test
  void palindromeMessageTest() {

    Set<ConstraintViolation<Foo>> constraintViolations = validator.validate(new Foo("foo"));
    String message = constraintViolations.stream().findFirst().get().getMessage();
    Assertions.assertEquals("Data bukan palindrome", message);
  }
}
