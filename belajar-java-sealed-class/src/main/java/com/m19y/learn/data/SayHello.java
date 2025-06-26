package com.m19y.learn.data;

public sealed interface SayHello permits Human, Dog, Cat{
  String hello();
}
