package com.m19y.learn.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeaderController {

  @ResponseBody
  @GetMapping(path = "/header/token")
  public String header(@RequestHeader(name = "X-TOKEN") String token){
    if(token.equals("Bro")){
      return "You are good!";
    }
    return "You are not good!";
  }
}
