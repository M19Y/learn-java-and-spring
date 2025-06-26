package com.m19y.learn.controller;

import com.m19y.learn.entity.User;
import com.m19y.learn.model.auth.LoginUserRequest;
import com.m19y.learn.model.auth.TokenResponse;
import com.m19y.learn.model.web.WebResponse;
import com.m19y.learn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(path = "/login")
  public ResponseEntity<WebResponse<TokenResponse>> login(@RequestBody LoginUserRequest request){
    TokenResponse token = authService.login(request);
    WebResponse<TokenResponse> response = WebResponse.<TokenResponse>builder().data(token).build();

    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping(path = "/logout")
  public ResponseEntity<Void> logout(User user){
    authService.logout(user);
    return ResponseEntity.noContent().build();
  }
}
