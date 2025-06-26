package com.m19y.learn.data;

import com.m19y.learn.annotation.NotBlank;

public class CreatePersonRequest {

  @NotBlank
  private String firstname;

  @NotBlank
  private String lastname;

  @NotBlank(allowEmptyString = true)
  private String phoneNumber;

  public CreatePersonRequest() {
  }

  public CreatePersonRequest(String firstname, String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public CreatePersonRequest(String firstname, String lastname, String phoneNumber) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
