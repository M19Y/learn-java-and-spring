package com.m19y.learn.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorController {


  // method argument not valid exception is for controller
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException ex){
    log.error("Method Argument Not Valid Exception -> {}", ex.getMessage());
    return ResponseEntity.badRequest().body("MANVE Validation error: " + ex.getMessage());
  }

  // constraint violation exception is for service
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> constraintViolationException(ConstraintViolationException ex){
    log.error("Constraint Violation Exception -> {}", ex.getMessage());
    return ResponseEntity.badRequest().body("CV Validation error: " + ex.getMessage());
  }
}
