package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringBuilderTest {

  @Test
  void create() {

    StringBuilder builder = new StringBuilder();
    builder.append("Learn");
    builder.append(" ");
    builder.append("Java");
    builder.append(" ");
    builder.append("Standard");
    builder.append(" ");
    builder.append("Classes");

    Assertions.assertEquals("Learn Java Standard Classes", builder.toString());
  }
}
