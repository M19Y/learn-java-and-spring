package com.m19y.learn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrintStreamTest {
  @Test
  void create() {

    PrintStream stream = System.out;

    stream.println("Bro");
  }

  @Test
  void withOutputStream() {

    try (OutputStream outputStream = Files.newOutputStream(Path.of("print-stream.txt"));
         PrintStream stream = new PrintStream(outputStream)) {
      stream.println("First");
      stream.println("second");
      stream.flush();
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }
}
