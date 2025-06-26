package com.m19y.learn.extractor;

import com.m19y.learn.container.DataInteger;
import jakarta.validation.valueextraction.ExtractedValue;
import jakarta.validation.valueextraction.UnwrapByDefault;
import jakarta.validation.valueextraction.ValueExtractor;

@UnwrapByDefault
public class DataIntegerValueExtractor implements ValueExtractor<@ExtractedValue(type = Integer.class) DataInteger> {

  @Override
  public void extractValues(@ExtractedValue(type = Integer.class) DataInteger originalValue, ValueReceiver receiver) {
    receiver.value(null, originalValue.getData());
  }
}
