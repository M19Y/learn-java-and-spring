package com.m19y.learn.controller;

import com.m19y.learn.model.CreatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class PersonController {

  @PostMapping(path = "/person/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> createPerson(@ModelAttribute @Valid CreatePersonRequest request,
                                             BindingResult bindingResult){

//    List<ObjectError> errors = bindingResult.getAllErrors();
    List<FieldError> errors = bindingResult.getFieldErrors();
    if (!errors.isEmpty()){
      errors.forEach(error -> {
        System.out.println(error.getField() + ": " + error.getDefaultMessage());
      });
      return ResponseEntity.badRequest().body("You send invalid data");
    }

    System.out.println(request);

    String response = new StringBuilder()
            .append("Success create person ")
            .append(request.getFirstName()).append(" ")
            .append(request.getMiddleName()).append(" ")
            .append(request.getLastName()).append(" ")
            .append("With Email ")
            .append(request.getEmail()).append(" ")
            .append("And Phone ")
            .append(request.getPhone()).append(" ")
            .append("With Address ")
            .append(request.getAddress().getCity()).append(", ")
            .append(request.getAddress().getStreet()).append(", ")
            .append(request.getAddress().getCountry()).append(", ")
            .append(request.getAddress().getPostalCode())
            .toString();

    return ResponseEntity.ok(response);
  }
}
