package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.io.StringWriter;

public class MemoryStreamTest {

  @Test
  void create() {

    StringWriter writer = new StringWriter();

    for (int i = 0; i < 10; i++) {
      writer.write("Hello guys\n");
      writer.flush();
    }

    String content = writer.getBuffer().toString();
    System.out.println(content);
  }
}
