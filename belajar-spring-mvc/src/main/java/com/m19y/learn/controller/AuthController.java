package com.m19y.learn.controller;

import com.m19y.learn.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class AuthController {


  @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<String> login(@RequestParam(name = "username") String username,
                                      @RequestParam(name = "password") String password,
                                      HttpServletRequest request,
                                      HttpServletResponse response){

    if(username.equals("Son Gohan") && password.equals("Secret")){

      // add session
      HttpSession session = request.getSession(true);
      session.setAttribute("user", new User(username));

      // add cookie
      String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8);
      Cookie cookie =  new Cookie("username", encodedUsername);
      cookie.setPath("/");
      response.addCookie(cookie);

      return ResponseEntity.status(HttpStatus.OK).body("Valid Credential");
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credential");
  }

  @ResponseBody
  @GetMapping(path = "/auth/user")
  public String getUser(@CookieValue("username") String username){
    String decodeUsername = URLDecoder.decode(username, StandardCharsets.UTF_8);
    return String.format("Hello %s", decodeUsername);
  }
}
