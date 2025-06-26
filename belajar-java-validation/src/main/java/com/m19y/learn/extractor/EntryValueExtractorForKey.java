package com.m19y.learn.extractor;

import com.m19y.learn.container.Entry;
import jakarta.validation.valueextraction.ExtractedValue;
import jakarta.validation.valueextraction.ValueExtractor;

public class EntryValueExtractorForKey implements ValueExtractor<Entry<@ExtractedValue ?, ?>> {

  @Override
  public void extractValues(Entry<?, ?> originalValue, ValueReceiver receiver) {
    receiver.keyedValue(null, "key", originalValue.getKey());
  }
}
