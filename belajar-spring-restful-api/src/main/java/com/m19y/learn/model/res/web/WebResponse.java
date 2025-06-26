package com.m19y.learn.model.res.web;

public interface WebResponse<T> {
  T getData();
  void setData(T data);
}
