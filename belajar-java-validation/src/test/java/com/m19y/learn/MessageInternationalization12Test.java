package com.m19y.learn;

import com.m19y.learn.group.CreditCardPaymentGroup;
import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.MessageInterpolator;
import org.hibernate.validator.internal.engine.MessageInterpolatorContext;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class MessageInternationalization12Test extends AbstractValidatorTest {

  @Test
  void testInternationalizationUseLocalIndonesia() {

    Locale.setDefault(Locale.of("in", "ID"));
    Payment payment = new Payment();
    payment.setAmount(10L);
    payment.setOrderId("1");

    Set<ConstraintViolation<Payment>> validate = validator.validate(payment, CreditCardPaymentGroup.class);

    Set<String> collect = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

    Assertions.assertTrue(collect.contains("jumlah karakter id pesanan harus antara 2 dan 10"));
    Assertions.assertTrue(collect.contains("jumlah harga harus antara 10000 dan 100000000"));
  }

  @Test
  void testMessageInterpolation() {

    Payment payment = new Payment();
    payment.setAmount(10L);
    payment.setOrderId("1");

    Locale locale = Locale.of("in", "ID");

    Set<ConstraintViolation<Object>> violations = validator.validate(payment, CreditCardPaymentGroup.class);
    for (ConstraintViolation<Object> violation : violations) {
      System.out.println("\nMessage: " + violation.getMessage());
      System.out.println("Message Template: " + violation.getMessageTemplate());

      MessageInterpolator.Context context = new MessageInterpolatorContext(
              violation.getConstraintDescriptor(),
              violation.getInvalidValue(),
              violation.getRootBeanClass(),
              violation.getPropertyPath(),
              violation.getConstraintDescriptor().getAttributes(),
              violation.getConstraintDescriptor().getAttributes(),
              ExpressionLanguageFeatureLevel.VARIABLES,
              true
      );

      String interpolateMessage = messageInterpolator.interpolate(violation.getMessageTemplate(), context, locale);
      System.out.println("Interpolate message: " + interpolateMessage);
      System.out.println("==========");
    }
  }
}
