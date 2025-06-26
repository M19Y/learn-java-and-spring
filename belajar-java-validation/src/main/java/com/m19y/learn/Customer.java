package com.m19y.learn;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Customer {

  @NotBlank(message = "name must not be blank")
  private String name;

  @Email(message = "email must valid format")
  @NotBlank(message = "email must not be blank")
  private String email;

  public Customer(String name, String email) {
    this.name = name;
    this.email = email;
  }


  public Customer() {
  }

  @Override
  public String toString() {
    return "Customer{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
