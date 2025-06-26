package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManipulationTest {

  @Test
  void fileManipulation() {

    Path path = Path.of("simple.txt");
    try {
      Files.createFile(path);
    } catch (IOException e) {
      System.err.println("Error create file : " + e.getMessage());
    }

    Assertions.assertTrue(Files.exists(path));

    try {
      Files.delete(path);
    } catch (IOException e) {
      System.out.println("Error delete file : " + e.getMessage());
    }

    Assertions.assertTrue(Files.notExists(path));
  }


  @Test
  void directoryManipulation() throws IOException {

    Path path = Path.of("simple-dir");
    Files.createDirectory(path);

    Assertions.assertTrue(Files.isDirectory(path));
    Assertions.assertTrue(Files.exists(path));

    Files.delete(path);
    Assertions.assertFalse(Files.exists(path));

  }
}
