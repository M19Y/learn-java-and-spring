package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {


  @Test
  void create() {

    String sentence = "Learn Java Standard Classes by Programmer Zaman Now";
    Pattern pattern = Pattern.compile("[a-zA-Z]*[a][a-zA-Z]*");
    Matcher matcher = pattern.matcher(sentence);

    while(matcher.find()){
      String result = matcher.group();
      System.out.println(result);
    }
  }
}
