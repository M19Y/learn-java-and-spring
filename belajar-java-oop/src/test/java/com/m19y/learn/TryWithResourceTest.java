package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourceTest {

  @Test
  void withoutTryWithResource() {

    BufferedReader reader = null;
    try{
      reader = new BufferedReader(new FileReader("READM.md"));

      while(true){
        String text = reader.readLine();
        if(text == null){
          break;
        }
        System.out.println(text);
      }
    }catch (Throwable throwable){
      System.out.println("Error read file: " + throwable.getMessage());
    }finally {
      if(reader != null){
        try {
          reader.close();
          System.out.println("Success menutup");
        }catch (IOException e){
          System.out.println("Error close resource: " + e.getMessage());
        }
      }
    }
  }

  @Test
  void withTryWithResource() {

    try(BufferedReader reader = new BufferedReader(new FileReader("README.md"))){
      while(true){
        String text = reader.readLine();
        if(text == null){
          break;
        }
        System.out.println(text);
      }
    }catch (Throwable throwable){
      System.out.println("Error read file: " + throwable.getMessage());
    }
  }
}
