package com.m19y.learn.model.res.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WebResponseImpl<T> extends BaseWebResponse<T> {

  public static <T> WebResponseImpl<T> of(T data) {
    WebResponseImpl<T> response = new WebResponseImpl<>();
    response.setData(data);
    return response;
  }
}
