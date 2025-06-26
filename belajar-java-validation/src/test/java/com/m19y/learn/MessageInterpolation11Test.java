package com.m19y.learn;

import com.m19y.learn.group.CreditCardPaymentGroup;
import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

public class MessageInterpolation11Test extends AbstractValidatorTest {

  @Test
  void testErrorRangeAndSizePayment() {

    Payment payment = new Payment();
    payment.setOrderId("1");
    payment.setAmount(10L);

    Set<ConstraintViolation<Payment>> validate = validator.validate(payment, CreditCardPaymentGroup.class);

    Set<String> validateMessage = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

    validateMessage.forEach(System.out::println);
    Assertions.assertTrue(validateMessage.contains("order id must between 2 and 10"));
    Assertions.assertTrue(validateMessage.contains("amount must be between 10000 and 100000000"));
  }
}
