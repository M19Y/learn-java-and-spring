package com.m19y.learn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderTest {

  @Test
  void read() {

    Path path = Path.of("output.txt");

    try (Reader reader = Files.newBufferedReader(path)) {
      StringBuilder builder = new StringBuilder();
      int data;
      int counter = 0;
      while ((data = reader.read()) != -1) {
        builder.append((char) data);
        counter++;
      }
      System.out.println(counter);
      System.out.println(builder);
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void readChars() {

    Path path = Path.of("output.txt");

    try (Reader reader = Files.newBufferedReader(path)) {
      StringBuilder builder = new StringBuilder();
      char[] chars = new char[1024];
      int length;
      int counter = 0;
      while ((length = reader.read(chars)) != -1) {
        builder.append(chars, 0, length);
        counter++;
        System.out.println(length);
      }
      System.out.println(counter);
      System.out.println(builder);
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }
}
