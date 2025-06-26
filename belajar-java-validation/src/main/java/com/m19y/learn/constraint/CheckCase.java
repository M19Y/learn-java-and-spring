package com.m19y.learn.constraint;

import com.m19y.learn.enums.CaseMode;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {CheckCaseValidator.class}
)
@Target(value = {ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CheckCase {

  CaseMode mode();

  String message() default "invalid case format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
