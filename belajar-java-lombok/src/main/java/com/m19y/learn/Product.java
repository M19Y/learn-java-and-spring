package com.m19y.learn;

import lombok.*;

// before

// @ToString
// @Getter@Setter
// @EqualsAndHashCode
// @RequiredArgsConstructor

// after
@Data

// with override method
@ToString(exclude = {"price"})
public class Product {

  private final String id;
  private String name;
  private Long price;
}
