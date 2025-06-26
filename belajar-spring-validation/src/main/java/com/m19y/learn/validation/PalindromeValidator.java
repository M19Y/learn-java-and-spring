package com.m19y.learn.validation;

import com.m19y.learn.helper.StringHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component // <- boleh tapi tidak wajib
public class PalindromeValidator implements ConstraintValidator<Palindrome, String> {

  @Autowired
  private StringHelper helper;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return helper.isPalindrome(value);
  }
}
