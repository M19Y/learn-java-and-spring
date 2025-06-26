package com.m19y.learn;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileHelper {

  @SneakyThrows
  public static String readFile(String file){
    @Cleanup FileReader fileReader = new FileReader(file);
    @Cleanup Scanner scanner = new Scanner(fileReader);

    StringBuilder builder = new StringBuilder();
    while(scanner.hasNext()){
      builder.append(scanner.nextLine()).append("\n");
    }

    return builder.toString();
  }
}
