package com.m19y.learn.data;

import com.m19y.learn.validation.Palindrome;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foo {

//  @Palindrome(message = "Must be palindrome")
  @Palindrome
  private String foo;
}
