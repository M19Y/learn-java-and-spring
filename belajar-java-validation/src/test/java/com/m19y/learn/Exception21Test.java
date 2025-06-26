package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Exception21Test extends AbstractValidatorTest {

  @Test
  void testValidateThrowException() {
    Car car = new Car();
    car.setId("1");
    car.setName("toyota");

    ConstraintViolationException constraintViolationException =
            Assertions.assertThrows(ConstraintViolationException.class, () -> validationWithThrowException(car));

    String[] messages = constraintViolationException.getMessage().split(", ");
    Assertions.assertEquals(2, messages.length);
    for (String message : messages) {
      System.out.println(message);
    }

  }
}
