package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class InputStreamTest {

  @Test
  void read() {

    Path path = Path.of("pom.xml");

    try (InputStream inputStream = Files.newInputStream(path)) {
      StringBuilder builder = new StringBuilder();
      int data;
      int counter = 0;
      while ((data = inputStream.read()) != -1) {
        builder.append((char) data);
        counter++;
      }
      System.out.print(builder);
      System.out.println(counter);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void readBytes() {
    Path path = Path.of("pom.xml");

    try (InputStream stream = Files.newInputStream(path)) {
      StringBuilder builder = new StringBuilder();
      byte[] bytes = new byte[1024];
      int length;
      int counter = 0;
      while ((length = stream.read(bytes)) != -1) {
        builder.append(new String(bytes, 0, length));
        System.out.println(length);
        counter++;
      }
      System.out.print(builder.toString());
      System.out.println(counter);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // ini adalah contoh sederhana yang dilakukan oleh stream.read()
  private static int modifyArray(int[] ref) {
    int i = 0;
    for (; i < ref.length; i++) {
      ref[i] = i;
    }
    return i;
  }
}
