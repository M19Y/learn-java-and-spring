package com.m19y.learn.data;

public sealed interface Shape permits Circle, Rectangle, Triangle{
  Long area();
}
