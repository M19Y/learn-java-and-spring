package com.m19y.learn.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

// harus menambahkan ini
@SupportedValidationTarget(value = {ValidationTarget.PARAMETERS})
public class CheckPasswordParameterValidator implements ConstraintValidator<CheckPasswordParameter, Object[]> {

  private int passwordParam;
  private int retypePasswordParam;

  @Override
  public boolean isValid(Object[] value, ConstraintValidatorContext context) {
    String password = (String) value[passwordParam];
    String retypePassword = (String) value[retypePasswordParam];

    if(password == null || retypePassword == null) return true;

    return password.equals(retypePassword);
  }

  @Override
  public void initialize(CheckPasswordParameter constraintAnnotation) {
    passwordParam = constraintAnnotation.passwordParam();
    retypePasswordParam = constraintAnnotation.retypePasswordParam();
  }
}
