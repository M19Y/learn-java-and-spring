package com.m19y.learn.controller;

import com.m19y.learn.model.web.WebErrorResponse;
import com.m19y.learn.model.web.WebResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {

  // cara menggunakan Map<String, String>
  // this is for manual validate
  @ExceptionHandler(value = ConstraintViolationException.class)
  public ResponseEntity<Map<String, String>> constraintViolationException(ConstraintViolationException ex){

    Map<String, String> errors = ex.getConstraintViolations().stream()
            .collect(Collectors.toMap(
                    violation -> violation.getPropertyPath().toString(),
                    ConstraintViolation::getMessage));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  // cara menggunakan String
//  @ExceptionHandler(value = ConstraintViolationException.class)
//  public ResponseEntity<WebErrorResponse> constraintViolationException(ConstraintViolationException ex) {
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new WebErrorResponse(ex.getMessage()));
//  }

  @ExceptionHandler(value = ResponseStatusException.class)
  public ResponseEntity<WebErrorResponse> constraintViolationException(ResponseStatusException ex){
    WebErrorResponse webErrorResponse = new WebErrorResponse(ex.getReason());
    return ResponseEntity.status(ex.getStatusCode())
            .body(webErrorResponse);
  }

  // this is for annotation @Valid and @Validate
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors()
            .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    return ResponseEntity.badRequest().body(errors);
  }
}
