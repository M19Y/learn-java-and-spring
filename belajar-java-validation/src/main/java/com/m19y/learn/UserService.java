package com.m19y.learn;

import com.m19y.learn.constraint.CheckPasswordParameter;
import jakarta.validation.constraints.NotBlank;

public class UserService {

  @CheckPasswordParameter(
          passwordParam = 1,
          retypePasswordParam = 2
  )
  public void register(@NotBlank(message = "username must not blank") String username,
                       @NotBlank(message = "password must not blank") String password,
                       @NotBlank(message = "retypePassword must not blank") String retypePassword
                       ){
    // TODO register
  }
}
