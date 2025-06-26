package com.m19y.learn;

import com.m19y.learn.constraint.CheckPassword;
import jakarta.validation.constraints.NotBlank;

@CheckPassword
public class Register {

  @NotBlank(message = "username can not blank")
  private String username;

  @NotBlank(message = "password can not blank")
  private String password;

  @NotBlank(message = "retype password can not blank")
  private String retypePassword;

  @Override
  public String toString() {
    return "Register{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", retypePassword='" + retypePassword + '\'' +
            '}';
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRetypePassword() {
    return retypePassword;
  }

  public void setRetypePassword(String retypePassword) {
    this.retypePassword = retypePassword;
  }
}
