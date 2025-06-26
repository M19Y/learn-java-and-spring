package com.m19y.learn.model.contact;

public record ContactResponse(
  String id,
  String firstName,
  String lastName,
  String email,
  String phone
){};
