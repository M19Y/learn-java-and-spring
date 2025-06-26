package com.m19y.learn.model;

public class Company {

  private String name;
  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return name;
  }
  public class Employee {
    private String name;

    public String getName(){
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
    public String getCompany(){
      return Company.this.name;
    }
  }
}
