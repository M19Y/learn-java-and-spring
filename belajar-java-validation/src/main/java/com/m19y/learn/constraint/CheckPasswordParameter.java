package com.m19y.learn.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(
        validatedBy = {CheckPasswordParameterValidator.class}
)
@Target(value = {ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CheckPasswordParameter {

  int passwordParam();
  int retypePasswordParam();

  String message() default "password and retype password must same";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
