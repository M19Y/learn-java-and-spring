package com.m19y.learn;

import com.m19y.learn.container.Data;
import com.m19y.learn.container.DataInteger;
import com.m19y.learn.container.Entry;
import com.m19y.learn.extractor.DataIntegerValueExtractor;
import com.m19y.learn.extractor.DataValueExtractor;
import com.m19y.learn.extractor.EntryValueExtractorForKey;
import com.m19y.learn.extractor.EntryValueExtractorForValue;
import com.m19y.learn.util.AbstractValidatorTest;
import jakarta.validation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ValueExtraction20Test extends AbstractValidatorTest {
  @Test
  void testSampleDataThrows() {

    SampleData sampleData = new SampleData();
    sampleData.setData(new Data<>());
    sampleData.getData().setData(" ");

    ConstraintDeclarationException constraintDeclarationException = Assertions.assertThrows(ConstraintDeclarationException.class, () -> validator.validate(sampleData));
    System.out.println(constraintDeclarationException.getMessage());
  }

  @Test
  void testSampleDataSuccess() {

    SampleData sampleData = new SampleData();
    sampleData.setData(new Data<>());
    sampleData.getData().setData(" ");

    ValidatorFactory validatorFactoryProvider = Validation.byDefaultProvider().configure()
            .addValueExtractor(new DataValueExtractor()).buildValidatorFactory();

    Validator validatorProvider = validatorFactoryProvider.getValidator();

    Set<ConstraintViolation<SampleData>> violations = validatorProvider.validate(sampleData);

    for (ConstraintViolation<SampleData> violation : violations) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println("====\n");
    }

  }

  @Test
  void testSampleEntryForKeyValuePairsThrows() {

    SampleEntry sampleEntry = new SampleEntry();
    sampleEntry.setEntry(new Entry<>());
    sampleEntry.getEntry().setKey(" ");
    sampleEntry.getEntry().setValue(" ");

    ConstraintDeclarationException constraintDeclarationException =
            Assertions.assertThrows(ConstraintDeclarationException.class, () -> validator.validate(sampleEntry));
    System.out.println(constraintDeclarationException.getMessage());
  }

  @Test
  void testSampleEntrySuccess() {

    SampleEntry sampleEntry = new SampleEntry();
    sampleEntry.setEntry(new Entry<>());
    sampleEntry.getEntry().setKey(" ");
    sampleEntry.getEntry().setValue(" ");

    ValidatorFactory validatorFactoryProvider = Validation.byDefaultProvider().configure()
            .addValueExtractor(new EntryValueExtractorForKey())
            .addValueExtractor(new EntryValueExtractorForValue())
            .buildValidatorFactory();

    Validator validatorProvider = validatorFactoryProvider.getValidator();

    Set<ConstraintViolation<SampleEntry>> violations = validatorProvider.validate(sampleEntry);

    for (ConstraintViolation<SampleEntry> violation : violations) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println("====\n");
    }
  }

  @Test
  void testSampleDataInteger() {

    SampleDataInteger sampleDataInteger = new SampleDataInteger();
    sampleDataInteger.setDataInteger(new DataInteger());
    sampleDataInteger.getDataInteger().setData(0);

    ConstraintDeclarationException constraint = Assertions.assertThrows(ConstraintDeclarationException.class, () -> {
      validator.validate(sampleDataInteger);
    });

    System.out.println(constraint.getMessage());
  }

  @Test
  void testSampleDataIntegerSuccess() {

    SampleDataInteger sampleDataInteger = new SampleDataInteger();
    sampleDataInteger.setDataInteger(new DataInteger());
    sampleDataInteger.getDataInteger().setData(0);

    ValidatorFactory validatorFactoryProvider = Validation.byDefaultProvider().configure()
            .addValueExtractor(new DataIntegerValueExtractor())
            .buildValidatorFactory();

    Validator validatorProvider = validatorFactoryProvider.getValidator();

    Set<ConstraintViolation<SampleDataInteger>> violations = validatorProvider.validate(sampleDataInteger);

    for (ConstraintViolation<SampleDataInteger> violation : violations) {
      System.out.println(violation.getPropertyPath());
      System.out.println(violation.getMessage());
      System.out.println("====\n");
    }
  }
}
