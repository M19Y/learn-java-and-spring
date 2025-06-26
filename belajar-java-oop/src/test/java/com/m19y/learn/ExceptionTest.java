package com.m19y.learn;

import com.m19y.learn.model.LoginRequest;
import com.m19y.learn.model.exception.ValidationException;
import com.m19y.learn.model.exception.ValidationUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

  @Test
  void create() {

    LoginRequest loginRequest = new LoginRequest(null, "Vro123");
    try {
      ValidationUtil.validate(loginRequest);
    }catch (ValidationException e){
      System.out.println("Terjadi error blank dengan pesan: " + e.getMessage());
    }catch (NullPointerException e){
      System.out.println("Terjadi error null dengan pesan: " + e.getMessage());

    }
  }

  @Test
  void successLogin() {
    LoginRequest loginRequest = new LoginRequest("Vro", "Vro123");
    Assertions.assertDoesNotThrow(() -> ValidationUtil.validate(loginRequest));
  }

  @Test
  void errorLoginNullUsername() {
    LoginRequest loginRequest = new LoginRequest(null, "Vro123");
    NullPointerException e = Assertions.assertThrows(NullPointerException.class, () -> ValidationUtil.validate(loginRequest));
    Assertions.assertEquals("Username tidak boleh null", e.getMessage());
  }

  @Test
  void errorLoginBlankUsername() {
    LoginRequest loginRequest = new LoginRequest("   ", "Vro123");
    ValidationException e = Assertions.assertThrows(ValidationException.class, () -> ValidationUtil.validate(loginRequest));
    Assertions.assertEquals("Username tidak boleh blank", e.getMessage());
  }

  //... the rest will follow
}
