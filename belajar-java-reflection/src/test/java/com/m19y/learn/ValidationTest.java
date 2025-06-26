package com.m19y.learn;

import com.m19y.learn.data.CreatePersonRequest;
import com.m19y.learn.validation.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationTest {


  @Test
  void testValidation() {

    RuntimeException firstnameBlank = Assertions.assertThrows(RuntimeException.class, () -> {
      CreatePersonRequest request = new CreatePersonRequest();
      Validation.validationReflection(request);
    });
    Assertions.assertEquals("Field firstname Must be not blank!", firstnameBlank.getMessage());

    RuntimeException firstnameBlank2 = Assertions.assertThrows(RuntimeException.class, () -> {
      CreatePersonRequest request = new CreatePersonRequest();
      request.setLastname("Musk");
      Validation.validationReflection(request);
    });
    Assertions.assertEquals("Field firstname Must be not blank!", firstnameBlank2.getMessage());

    RuntimeException lastnameBlank = Assertions.assertThrows(RuntimeException.class, () -> {
      CreatePersonRequest request = new CreatePersonRequest();
      request.setFirstname("Elon");
      Validation.validationReflection(request);
    });
    Assertions.assertEquals("Field lastname Must be not blank!", lastnameBlank.getMessage());

    Assertions.assertDoesNotThrow(() -> {
      CreatePersonRequest request = new CreatePersonRequest();
      request.setFirstname("Elon");
      request.setLastname("Musk");
      request.setPhoneNumber(" ");
      Validation.validationReflection(request);
    });

    // clean
    Assertions.assertDoesNotThrow(() -> {
      CreatePersonRequest request = new CreatePersonRequest();
      request.setFirstname("Elon");
      request.setLastname("Musk");
      request.setPhoneNumber(" ");
      Validation.validateNotBlankFields(request);
    });

  }
}
