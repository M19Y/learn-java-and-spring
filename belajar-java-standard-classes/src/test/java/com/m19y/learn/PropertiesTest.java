package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

public class PropertiesTest {
  @Test
  void create() {

    try{
      Properties properties = new Properties();
      properties.load(new FileInputStream("application.properties"));
      System.out.println(properties.getProperty("name.first"));
    }catch (IOException e){
      System.err.println(e.getMessage());
    }
  }

  @Test
  void store() {
    try {
      Properties properties = new Properties();
      properties.put("simple.id", "1");
      properties.put("simple.group", "The Brothers");

      properties.store(new FileOutputStream("simple.properties"), "simple comment");
    } catch (FileNotFoundException e) {
      System.out.println("Error membuat file properties");
    } catch (IOException e){
      System.out.println("Error menyimpan properties");
    }

  }

  @Test
  void readFromResourceTest() {
    try{
      InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
      Properties properties = new Properties();
      properties.load(input);
      System.out.println(properties.getProperty("name.first"));
    }catch (IOException e){
      System.err.println(e.getMessage());
    }
  }

}
