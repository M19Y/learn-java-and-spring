package com.m19y.learn.model.res.web;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class BaseWebResponse<T> implements WebResponse<T> {
  private T data;
}
