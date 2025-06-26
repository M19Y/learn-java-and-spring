package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest {

  @Test
  void testAccessLevel() {

    Login login = Login.createEmpty();
    login.setUsername("root");
    login.setPassword("root");

    Assertions.assertEquals("root", login.getUsername());
    Assertions.assertEquals("root", login.getPassword());

    // ! see the changes in the target -> classes -> Login
  }

  @Test
  void testStaticConstructor() {

    Login login1 = Login.create("root", "root");
    Login login2 = Login.createEmpty();

    Assertions.assertEquals("root", login1.getUsername());
    Assertions.assertEquals("root", login1.getPassword());
    Assertions.assertNull(login2.getUsername());
    Assertions.assertNull(login2.getPassword());

  }

  @Test
  void toStringWithExcludeTest() {
    Login login = Login.create("bro", "bro");
    System.out.println(login);
    Assertions.assertEquals("Login(username=bro)", login.toString());
  }
}
