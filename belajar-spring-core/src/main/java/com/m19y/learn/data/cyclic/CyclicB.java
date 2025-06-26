package com.m19y.learn.data.cyclic;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CyclicB {

  private CyclicC cyclicC;
}
