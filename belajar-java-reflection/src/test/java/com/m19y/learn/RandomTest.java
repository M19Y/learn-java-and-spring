package com.m19y.learn;

import com.m19y.learn.annotation.NotBlank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomTest {

  @Test
  void testBitAndNormalNumber() {

    Assertions.assertEquals(10, 0b1010);
  }

  @Test
  void rootAnnotation() {
    Class<NotBlank> notBlankClass = NotBlank.class;
    System.out.println(notBlankClass.getSuperclass());
  }

  @Test
  void stringTest() {

    Assertions.assertTrue("".isBlank()); // true
    Assertions.assertTrue(" ".isBlank()); // true
    Assertions.assertTrue("".isEmpty()); // true
    Assertions.assertFalse(" ".isEmpty()); // false
  }
}
