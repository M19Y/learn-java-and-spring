package com.m19y.learn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriterTest {

  @Test
  void create() {

    Path path = Path.of("simple-output.txt");

    try (Writer writer = Files.newBufferedWriter(path)) {
      for (int i = 0; i < 10; i++) {
        writer.write(String.format("Simple %d\n", i + 1));
        writer.flush();
      }
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }
}
