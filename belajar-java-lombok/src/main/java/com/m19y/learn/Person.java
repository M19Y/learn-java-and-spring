package com.m19y.learn;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Person {

  private String id;
  private String name;
  private Integer age;
  @Singular
  private List<String> hobbies;

}
