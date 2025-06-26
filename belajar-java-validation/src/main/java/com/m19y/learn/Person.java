package com.m19y.learn;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person {

  @Size(max = 20, message = "firstname must be 20 characters long")
  @NotBlank(message = "firstname must be not blank")
  private String firstname;

  @Size(max = 20, message = "lastname must be 20 characters long")
  @NotBlank(message = "lastname must be not blank")
  private String lastname;

  @NotNull(message = "address can not be null")
  @Valid // this constraint will validate nested constraint
  private Address address;

  @Valid
  public Person() {
  }

  @Valid
  public Person(@NotBlank(message = "firstname can not blank") String firstname,
                @NotBlank(message = "lastname can not blank")String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }

  @Valid
  public Person(@NotBlank(message = "firstname can not blank") String firstname,
                @NotBlank(message = "lastname can not blank") String lastname,
                @NotNull(message = "address can not null") @Valid Address address) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
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

  @Override
  public String toString() {
    return "Person{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  // constraint for parameter
  public void sayHello(@NotBlank(message = "name can not blank") String name){
    System.out.println("Hello " + name + " my name is " + firstname);
  }

  // constraint for return value
  @NotBlank(message = "full name can not blank")
  public String fullName(){
    return firstname + " " + lastname;
  }


}
