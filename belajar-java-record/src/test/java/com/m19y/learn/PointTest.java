package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointTest {

  @Test
  void compactConstructorTest() {

    Point point = new Point(10, 10);
    Assertions.assertNotNull(point);
  }

  @Test
  void staticFieldAndMethod() {

    Assertions.assertEquals(0, Point.ZERO.x());
    Assertions.assertEquals(0, Point.ZERO.y());

    Point point = Point.create(10, 10);
    Assertions.assertEquals(10, point.x());
    Assertions.assertEquals(10, point.y());
  }
}
