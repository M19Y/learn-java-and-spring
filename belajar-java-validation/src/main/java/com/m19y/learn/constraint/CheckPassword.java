package com.m19y.learn.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {CheckPasswordValidator.class}
)
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CheckPassword {

  String message() default "password and retype password must same";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
