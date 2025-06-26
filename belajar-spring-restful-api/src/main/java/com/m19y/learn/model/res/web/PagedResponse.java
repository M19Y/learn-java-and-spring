package com.m19y.learn.model.res.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagedResponse<T> extends BaseWebResponse<T> {

  private Integer totalPage;
  private Integer currentPage;
  private Integer size;

  public static <T> PagedResponse<T> of(T data, int currentPage, int size, int totalPage) {
    PagedResponse<T> response = new PagedResponse<>();
    response.setData(data);
    response.setCurrentPage(currentPage);
    response.setSize(size);
    response.setTotalPage(totalPage);
    return response;
  }
}
