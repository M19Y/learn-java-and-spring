package com.m19y.learn;

import com.m19y.learn.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncapsulationTest {

  @Test
  void create() {

    Category category = new Category();
    category.setId("Simple ID");
    category.setExpensive(false);

    Assertions.assertEquals("Simple ID", category.getId());
    Assertions.assertFalse(category.isExpensive());

    Category category1 = new Category();
    category1.setExpensive(true);
    category1.setId("category-1");
    category1.setId(null);

    Assertions.assertNotNull(category1.getId());
    Assertions.assertEquals("category-1", category1.getId());
    Assertions.assertTrue(category1.isExpensive());
  }
}
