package com.m19y.learn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Manager extends Employee{

  private Integer totalEmployee;
}
