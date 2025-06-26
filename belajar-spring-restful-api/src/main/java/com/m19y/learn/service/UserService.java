package com.m19y.learn.service;

import com.m19y.learn.entity.User;
import com.m19y.learn.model.user.RegisterUserRequest;
import com.m19y.learn.model.user.UpdateUserRequest;
import com.m19y.learn.model.user.UserResponse;

public interface UserService {

  void register(RegisterUserRequest request);
  UserResponse get(User user);
  UserResponse update(User user, UpdateUserRequest request);

}
