package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstraintComposition14Test extends AbstractValidatorTest {


  @Test
  void testCompositionSimpleError() {

    Car car = new Car();
    car.setId(" ");
    viewViolation(car);

  }

  @Test
  void testCompositionError() {

    Car car = new Car();
    car.setId("b");
    viewViolation(car);

    Set<String> collect = validator.validate(car).stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
    Assertions.assertTrue(collect.contains("Car id must between 4 and 10 character"));
    Assertions.assertTrue(collect.contains("Car id must be an upper case"));
  }

  @Test
  void testCompositionLocaleError() {

    Locale.setDefault(Locale.of("in", "ID"));
    Car car = new Car();
    car.setId("b");
    viewViolation(car);

    Set<String> collect = validator.validate(car).stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
    Assertions.assertTrue(collect.contains("id mobil harus dalam bentuk KAPITAL"));
    Assertions.assertTrue(collect.contains("id mobil harus antara 4 dan 10 karakter"));
  }
}
