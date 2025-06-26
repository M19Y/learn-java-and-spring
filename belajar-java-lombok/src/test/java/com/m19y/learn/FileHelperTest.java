package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;


class FileHelperTest {

  @Test
  void readPomXMl(){
    String pomFile = FileHelper.readFile("pom.xml");
    System.out.println(pomFile);
  }

  @Test
  void testErrorRead() {

    Assertions.assertThrows(FileNotFoundException.class, () -> FileHelper.readFile("nothing.txt"));
  }
}