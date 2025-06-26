package com.m19y.learn;

import com.m19y.learn.model.Rectangle;
import com.m19y.learn.model.Shape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuperKeyTest {

  @Test
  void create() {

    Shape shape = new Shape();
    Rectangle rectangle = new Rectangle();

    Assertions.assertEquals(4, rectangle.getCorner());
    Assertions.assertEquals(shape.getCorner(), rectangle.getParentCorner());
  }
}
