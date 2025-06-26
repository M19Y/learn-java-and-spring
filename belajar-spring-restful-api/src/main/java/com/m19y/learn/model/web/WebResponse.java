package com.m19y.learn.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {

  private T data;

  public static <T> WebResponse<T> of(T data) {
    return WebResponse.<T>builder()
            .data(data)
            .build();
  }

}
