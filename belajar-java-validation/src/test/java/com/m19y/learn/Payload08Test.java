package com.m19y.learn;

import com.m19y.learn.group.CreditCardPaymentGroup;
import com.m19y.learn.payload.EmailErrorPayload;
import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Payload;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class Payload08Test extends AbstractValidatorTest {

  @Test
  void testPayload() {

    Payment payment = new Payment();
    payment.setAmount(20_000L);
    payment.setOrderId("0001");
    payment.setCreditCard("31111");

    Set<ConstraintViolation<Payment>> validate = validator.validate(payment, CreditCardPaymentGroup.class);
    for (ConstraintViolation<Payment> violation : validate) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println(violation.getConstraintDescriptor().getPayload());

      Set<Class<? extends Payload>> payload = violation.getConstraintDescriptor().getPayload();
      for (Class<? extends Payload> payloadClass : payload) {
        if(payloadClass == EmailErrorPayload.class){
          EmailErrorPayload emailErrorPayload = new EmailErrorPayload();
          emailErrorPayload.sendEmail(violation);
        }
      }
      System.out.println("=======\n");

    }
  }
}
