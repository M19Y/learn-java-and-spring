package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class SmallFileTest {

  @Test
  void create() throws IOException {

    Path path1 = Path.of("small1.txt");
    byte[] bytes = "Hello world 1".getBytes();
    System.out.println(Arrays.toString(bytes));
    Files.write(path1, bytes);

    Path path2 = Path.of("small2.txt");
    Files.writeString(path2, "Hello world 2");

    Assertions.assertTrue(Files.exists(path1));
    Assertions.assertTrue(Files.exists(path2));
  }

  @Test
  void read() throws IOException {
    Path path1 = Path.of("small1.txt");
    byte[] bytes = Files.readAllBytes(path1);
    System.out.println(Arrays.toString(bytes));
    String text1 = new String(bytes);

    Assertions.assertEquals("Hello world 1", text1);

    Path path2 = Path.of("small2.txt");
    String text2 = Files.readString(path2);

    Assertions.assertEquals("Hello world 2", text2);
  }
}
