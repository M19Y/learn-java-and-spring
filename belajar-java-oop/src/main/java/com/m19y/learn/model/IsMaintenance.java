package com.m19y.learn.model;

public interface IsMaintenance {

  /*
  * Kita bisa membuat field di interface dengan syarat
  * Field tersebut harus public, static dan final
  * Secara default interface akan membuat itu, tanpa harus kita menambahkannya
  */
  public static final int MAX_TOTAL_MAINTENANCE = 10;
  int MIN_TOTAL_MAINTENANCE = 2;

  boolean isMaintenance();
}
