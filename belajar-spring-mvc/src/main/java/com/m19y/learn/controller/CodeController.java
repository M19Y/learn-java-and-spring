package com.m19y.learn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CodeController {

  @DeleteMapping(path = "/products/{id}")
  @ResponseBody
  @ResponseStatus(code = HttpStatus.ACCEPTED)
  public void deleteProduct(@PathVariable("id") String productId){
    // delete product with id
  }
}
