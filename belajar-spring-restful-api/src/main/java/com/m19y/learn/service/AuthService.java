package com.m19y.learn.service;

import com.m19y.learn.entity.User;
import com.m19y.learn.model.auth.LoginUserRequest;
import com.m19y.learn.model.auth.TokenResponse;

public interface AuthService {

  TokenResponse login(LoginUserRequest request);
  void logout(User user);
}
