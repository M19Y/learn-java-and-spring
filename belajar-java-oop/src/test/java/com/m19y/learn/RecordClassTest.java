package com.m19y.learn;

import com.m19y.learn.model.LoginRequest;
import com.m19y.learn.model.Tokopedia;
import org.junit.jupiter.api.Test;

public class RecordClassTest {

  @Test
  void create() {

    LoginRequest login = new LoginRequest("Vro", "Vro123");
    System.out.println(login.password());
    System.out.println(login.username());
    System.out.println(login);

    System.out.println(login.toUpper());

  }

  @Test
  void overloadConstructor() {
    LoginRequest login = new LoginRequest("Sis");
    System.out.println(login.username());
    System.out.println(login.password());
  }

  @Test
  void loginSimulation() {

    Tokopedia tokopedia = new Tokopedia();
    tokopedia.login(new LoginRequest("Vro", "Vro123"));
  }
}
