package com.m19y.learn;

import com.m19y.learn.group.CreditCardPaymentGroup;
import com.m19y.learn.util.AbstractValidatorTest;
import org.junit.jupiter.api.Test;

public class GroupConvert07Test extends AbstractValidatorTest {

  @Test
  void anomalyTest() {
    Payment payment = new Payment();
    payment.setOrderId("001");
    payment.setVirtualAccount("simple-account");
    payment.setCreditCard("4111111111111111");
    payment.setAmount(20_000L);

    // this should throw an error, but it wasn't
    // even though we already defined @Valid annotation
    // we should convert the VirtualAccountPaymentGroup and CreditCardPaymentGroup to Default.class
    // using @ConvertGroup annotation
    payment.setCustomer(new Customer()); // here is the anomaly

    viewViolationWithGroup(payment, CreditCardPaymentGroup.class);
  }
}
