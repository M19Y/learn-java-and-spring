package com.m19y.learn;

import com.m19y.learn.group.PaymentGroup;
import com.m19y.learn.util.AbstractValidatorTest;
import org.junit.jupiter.api.Test;

public class GroupSequence06Test extends AbstractValidatorTest {

  @Test
  void testGroupSequence() {
    Payment payment =new Payment();

    viewViolationWithGroup(payment, PaymentGroup.class);
  }
}
