package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.util.StringTokenizer;

public class StringTokenizerTest {

  @Test
  void create() {

    String simple = "Learn Java Standard Classes";
    StringTokenizer token = new StringTokenizer(simple, " ");

    // split berbeda dengan string tokenizer, karena tokenizer adalah lazy, harus dipanggil nextTokennya
    while(token.hasMoreTokens()){
      String data = token.nextToken();
      System.out.println(data);

    }
  }
}
