package com.m19y.learn.model.web;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> extends WebResponse<T> {

  private Integer totalPage;
  private Integer currentPage;
  private Integer size;


  // bagaimana caranya agar data ini dapat menerima
  public static <T> PagedResponse<T> of(T data, int currentPage, int size, int totalPage) {
    PagedResponse<T> response = new PagedResponse<>();
    response.setData(data); // inherited from WebResponse
    response.setCurrentPage(currentPage);
    response.setSize(size);
    response.setTotalPage(totalPage);
    return response;
  }
}
