package com.m19y.learn;

import com.m19y.learn.container.Entry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SampleEntry {

  @NotNull
  Entry<@NotBlank String, @NotBlank String> entry;

  public Entry<String, String> getEntry() {
    return entry;
  }

  public void setEntry(Entry<String, String> entry) {
    this.entry = entry;
  }
}
