package com.m19y.learn.controller;

import com.m19y.learn.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class UserController {

  @GetMapping(path = "/user/current")
  @ResponseBody
  public String getUser(@SessionAttribute(name = "user") User user){
    return "Hello " + user.getUsername();
  }
}
