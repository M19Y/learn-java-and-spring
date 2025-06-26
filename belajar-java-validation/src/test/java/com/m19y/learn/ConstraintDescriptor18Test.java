package com.m19y.learn;

import com.m19y.learn.group.CreditCardPaymentGroup;
import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ConstraintDescriptor18Test extends AbstractValidatorTest {

  @Test
  void getConstraintDescriptorTest() {

//    Person person = new Person();
    Payment payment = new Payment();
    payment.setCreditCard("4111");

    Set<ConstraintViolation<Payment>> violations = validator.validate(payment, CreditCardPaymentGroup.class);

    violations.stream().map(ConstraintViolation::getConstraintDescriptor)
            .forEach(constraintDescriptor -> {
              System.out.println(constraintDescriptor);
              System.out.println("Annotation: " + constraintDescriptor.getAnnotation());
              System.out.println("Payload: " + constraintDescriptor.getPayload());
              System.out.println("Attributes: " + constraintDescriptor.getAttributes());
              System.out.println("Groups: " + constraintDescriptor.getGroups());
              System.out.println("Message template: " + constraintDescriptor.getMessageTemplate());
              System.out.println("======\n");
            });

  }
}
