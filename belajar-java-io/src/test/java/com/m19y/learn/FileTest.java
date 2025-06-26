package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileTest {

  @Test
  void fileNotExists() {

    File file = new File("simple.txt");

    Assertions.assertEquals("simple.txt", file.getName());
    Assertions.assertFalse(file.exists());
  }

  @Test
  void fileIsExists() {

    File file = new File("src/main/resources/application.properties");
    Assertions.assertEquals("application.properties", file.getName());
    Assertions.assertTrue(file.exists());
  }
}
