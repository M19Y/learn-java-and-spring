package com.m19y.learn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class OpenOptionTest {

  @Test
  void create() {

    Path path = Path.of("open-option-output.txt");

    try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

      for (int i = 1; i <= 10; i++) {
        writer.write(String.format("Simple %d\n", i));
        writer.flush();
      }

    } catch (IOException e) {
      Assertions.fail(e);
    }
  }
}
