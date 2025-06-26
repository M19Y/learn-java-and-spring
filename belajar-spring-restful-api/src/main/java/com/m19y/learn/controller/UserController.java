package com.m19y.learn.controller;

import com.m19y.learn.entity.User;
import com.m19y.learn.model.user.RegisterUserRequest;
import com.m19y.learn.model.user.UpdateUserRequest;
import com.m19y.learn.model.user.UserResponse;
import com.m19y.learn.model.web.WebResponse;
import com.m19y.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<String>> register(@RequestBody RegisterUserRequest request){
    userService.register(request);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(WebResponse.<String>builder().data("OK").build());
  }

  @GetMapping(path = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<UserResponse>> get(User user){
    UserResponse userResponse = userService.get(user);
    WebResponse<UserResponse> response = WebResponse.<UserResponse>builder()
            .data(userResponse)
            .build();
    return ResponseEntity.ok().body(response);
  }

  @PatchMapping(path = "/current",
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<WebResponse<UserResponse>> update(User user, UpdateUserRequest request){
    UserResponse userResponse = userService.update(user, request);
    WebResponse<UserResponse> response = WebResponse.<UserResponse>builder()
            .data(userResponse)
            .build();
    return ResponseEntity.ok().body(response);
  }
}
