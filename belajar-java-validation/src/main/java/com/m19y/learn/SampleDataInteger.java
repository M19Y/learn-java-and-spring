package com.m19y.learn;

import com.m19y.learn.container.DataInteger;
import jakarta.validation.constraints.Min;

public class SampleDataInteger {

  @Min(value = 10)
  DataInteger dataInteger;

  public DataInteger getDataInteger() {
    return dataInteger;
  }

  public void setDataInteger(DataInteger dataInteger) {
    this.dataInteger = dataInteger;
  }
}
