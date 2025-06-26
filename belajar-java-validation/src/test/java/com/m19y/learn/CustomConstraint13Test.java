package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomConstraint13Test extends AbstractValidatorTest {
  @Test
  void testCaseUpperFailed() {

    Car car = new Car();
    car.setId("123");
    car.setName("Lamborghini");

    Set<ConstraintViolation<Car>> validate = validator.validate(car);
    Set<String> collect = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

    Assertions.assertTrue(collect.contains("Car name must be upper case"));
  }

  @Test
  void testCaseUpperSuccess() {

    Car car = new Car();
    car.setId("123");
    car.setName("TOYOTA");

    Set<ConstraintViolation<Car>> validate = validator.validate(car);
    Set<String> collect = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

    Assertions.assertFalse(collect.contains("Car name must be upper case"));
  }

  @Test
  void testCaseUpper18NFailed() {

    Car car = new Car();
    car.setId("123");
    car.setName("Lamborghini");

    Locale.setDefault(Locale.of("in", "ID"));

    Set<ConstraintViolation<Car>> validate = validator.validate(car);
    Set<String> collect = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

    Assertions.assertTrue(collect.contains("nama mobil harus dalam bentuk KAPITAL"));
  }
}
