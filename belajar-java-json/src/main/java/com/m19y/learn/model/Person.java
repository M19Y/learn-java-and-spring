package com.m19y.learn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person {

  private String id;
  private String name;
  @JsonProperty(value = "full_name")
  private String fullName;
  @JsonIgnore
  private String password;
  private int age;
  private final List<String> hobbies = new ArrayList<>();
  private Address address;
  private Date createdAt;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private Date updatedAt;

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public List<String> getHobbies() {
    return hobbies;
  }

  public void addHobby(String hobby) {
    if(hobby != null){
      this.hobbies.add(hobby);
    }
  }
}
