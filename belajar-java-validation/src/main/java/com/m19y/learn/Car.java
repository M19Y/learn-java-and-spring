package com.m19y.learn;

import com.m19y.learn.constraint.CheckCarId;
import com.m19y.learn.constraint.CheckCase;
import com.m19y.learn.enums.CaseMode;

public class Car {

  @CheckCarId
  private String id;

  @CheckCase(message = "{car.name.upper}", mode = CaseMode.UPPER)
  private String name;

  public Car() {
  }

  public Car(String id, String name) {
    this.id = id;
    this.name = name;
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
}
