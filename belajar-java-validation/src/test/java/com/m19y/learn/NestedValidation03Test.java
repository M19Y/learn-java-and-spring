package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import org.junit.jupiter.api.Test;


public class NestedValidation03Test extends AbstractValidatorTest {

  @Test
  void testNested() {
    Person person = new Person("Elon", "Musk");
    Address address = new Address();
    person.setAddress(address);

    // by default, will be empty if we are not adding @Valid into address field at Person.class
    viewViolation(person);
  }
}
