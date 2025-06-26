package com.m19y.learn;

import lombok.*;

@Getter
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor(staticName = "createEmpty")
@ToString(exclude = {"password"})
public class Login {

  @Setter(AccessLevel.PROTECTED)
  private String username;
  @Setter(AccessLevel.PROTECTED)
  private String password;
}
