package com.m19y.learn.constraint;

import com.m19y.learn.enums.CaseMode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

  private CaseMode caseMode;
  @Override
  public void initialize(CheckCase constraintAnnotation) {
    caseMode = constraintAnnotation.mode();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if(s == null) return true;
    if(caseMode == CaseMode.UPPER){
      return s.equals(s.toUpperCase());
    } else if (caseMode == CaseMode.LOWER) {
      return s.equals(s.toLowerCase());
    }
    return false;
  }
}
