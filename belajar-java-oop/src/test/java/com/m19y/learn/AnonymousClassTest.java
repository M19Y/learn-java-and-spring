package com.m19y.learn;

import com.m19y.learn.model.HelloWorld;
import org.junit.jupiter.api.Test;

public class AnonymousClassTest {

  @Test
  void create() {

    HelloWorld english = new HelloWorld() {
      @Override
      public void sayHello() {
        System.out.println("Hello guys");
      }

      @Override
      public void sayHello(String name) {
        System.out.println("Hello " + name);
      }
    };

    english.sayHello();
    english.sayHello("Vro");
  }
}
