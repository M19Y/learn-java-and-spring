package com.m19y.learn.mapper;

import com.m19y.learn.entity.User;
import com.m19y.learn.model.user.UserResponse;

public class UserMapper {

  public static UserResponse toDto(User user){
    return new UserResponse(user.getUsername(), user.getName());
  }
}

