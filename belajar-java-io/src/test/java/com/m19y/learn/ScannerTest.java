package com.m19y.learn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ScannerTest {

  @Test
  void create() {

    try (InputStream stream = Files.newInputStream(Path.of("pom.xml"));
         Scanner scanner = new Scanner(stream)) {

      int count = 0;
      while (scanner.hasNext()) {
        String text = scanner.nextLine();
        count++;
        System.out.println(text);
      }
      System.out.println(count);
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }
}
