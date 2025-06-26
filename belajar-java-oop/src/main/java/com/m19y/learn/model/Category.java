package com.m19y.learn.model;

public class Category {

  private String id;
  private boolean expensive;

  public void setId(String id){
    if(id != null){
      this.id = id;
    }
  }

  public String getId(){
    return id;
  }

  public void setExpensive(boolean expensive){
    this.expensive = expensive;
  }

  public boolean isExpensive(){
    return expensive;
  }

}
