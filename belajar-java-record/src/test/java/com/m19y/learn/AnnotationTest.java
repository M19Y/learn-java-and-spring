package com.m19y.learn;

import com.m19y.learn.annotation.Valid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnnotationTest {

  @Test
  void testPointWithAnnotation() throws NoSuchFieldException, NoSuchMethodException {

    // check for properties or filed
    Assertions.assertNotNull(Point.class.getDeclaredField("x").getAnnotation(Valid.class));
    Assertions.assertNotNull(Point.class.getDeclaredField("y").getAnnotation(Valid.class));

    // check for method
    Assertions.assertNotNull(Point.class.getDeclaredMethod("x").getAnnotation(Valid.class));
    Assertions.assertNotNull(Point.class.getDeclaredMethod("y").getAnnotation(Valid.class));

    // check for constructor
    Assertions.assertNotNull(Point.class.getConstructors()[0].getParameters()[0].getAnnotation(Valid.class));
    Assertions.assertNotNull(Point.class.getConstructors()[0].getParameters()[1].getAnnotation(Valid.class));
  }
}
