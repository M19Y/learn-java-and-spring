package com.m19y.learn;

import com.m19y.learn.group.CreditCardPaymentGroup;
import com.m19y.learn.group.VirtualAccountPaymentGroup;
import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Group05Test extends AbstractValidatorTest {

  @Test
  void testHibernateValidatorConstraintInvalidCreditCard() {

    Payment payment = new Payment();
    payment.setAmount(1000L);
    payment.setCreditCard("433");
    payment.setOrderId("001");

    viewViolationWithGroup(payment, CreditCardPaymentGroup.class);

    // will be an error if we didn't call the group of the validating
    // Assertions.assertEquals(2, validator.validate(payment).size());

    // grouping
    Assertions.assertEquals(2, validator.validate(payment, CreditCardPaymentGroup.class).size());
  }

  @Test
  void testHibernateValidatorConstraintValidCreditCard() {

    Payment payment = new Payment();
    payment.setAmount(10_000_000L);
    payment.setCreditCard("4111111111111111");
    payment.setOrderId("001");

    viewViolationWithGroup(payment, CreditCardPaymentGroup.class);
    Assertions.assertEquals(0, validator.validate(payment, CreditCardPaymentGroup.class).size());
  }

  @Test
  void testHibernateValidatorConstraintInvalidVirtualAccount() {

    Payment payment = new Payment();
    payment.setAmount(10_000_000L);
    payment.setOrderId("001");
    payment.setVirtualAccount("");

    viewViolationWithGroup(payment, VirtualAccountPaymentGroup.class);

    Assertions.assertEquals(1, validator.validate(payment, VirtualAccountPaymentGroup.class).size());
    String message = validator.validate(payment, VirtualAccountPaymentGroup.class).stream()
                    .map(ConstraintViolation::getMessage)
                    .findFirst().orElse(null);

    Assertions.assertNotNull(message);
    Assertions.assertEquals("virtual account must not be blank", message);
  }
}
