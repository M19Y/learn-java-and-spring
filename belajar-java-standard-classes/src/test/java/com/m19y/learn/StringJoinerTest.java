package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

public class StringJoinerTest {

  @Test
  void create() {

    String[] simple = {"Learn", "Java", "Standard", "Classes"};
    StringJoiner stringJoiner = new StringJoiner("||", "<", ">");

    for (String data: simple){
      stringJoiner.add(data);
    }

    Assertions.assertEquals("<Learn||Java||Standard||Classes>", stringJoiner.toString());


  }
}
