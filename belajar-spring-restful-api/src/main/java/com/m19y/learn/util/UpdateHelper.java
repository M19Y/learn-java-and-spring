package com.m19y.learn.util;

import java.util.function.Consumer;

public class UpdateHelper {

  public static <T> void updateIfNotNull(T value, Consumer<T> setter) {
    if (value != null) {
      setter.accept(value);
    }
  }
}
