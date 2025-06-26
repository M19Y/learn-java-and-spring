package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.metadata.BeanDescriptor;
import org.junit.jupiter.api.Test;


public class MetadataTest extends AbstractValidatorTest {

  @Test
  void testMetadata() {
    BeanDescriptor constraintsForClass = validator.getConstraintsForClass(Car.class);

    constraintsForClass.getConstrainedProperties()
            .stream().peek(property -> System.out.println(property.getPropertyName()))
            .forEach(property -> property.getConstraintDescriptors().forEach(System.out::println));
  }
}
