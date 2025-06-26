package com.m19y.learn;

import lombok.Value;
import lombok.With;

@Value // sama seperti java record, semuanya private final, dan tidak bisa di extends
@With
public class Register {

  String username;
  String password;
}
