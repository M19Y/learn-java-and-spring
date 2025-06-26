package com.m19y.learn.service;

import com.m19y.learn.model.user.RegisterUserRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidatorService {

  private final Validator validator;

  @Autowired
  public ValidatorService(Validator validator) {
    this.validator = validator;
  }

  public void validate(Object o){
    Set<ConstraintViolation<Object>> violations = validator.validate(o);

    if(!violations.isEmpty()){
      throw new ConstraintViolationException(violations);
    }
  }
}
