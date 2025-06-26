package com.m19y.learn;

import com.m19y.learn.entity.Category;
import com.m19y.learn.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryRepository02Test {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    categoryRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    categoryRepository.deleteAll();
  }

  @Test
  void categoryShouldHaveId_whenCategorySaved() {
    Category category = new Category();
    category.setName("Phone");

    categoryRepository.save(category);

    assertNotNull(category.getId());
  }

  @Test
  void categoryShouldHaveAnUpdatedName_whenExistingCategoryNameUpdateUsingSave() {

    // setup existing category
    Category category = new Category();
    category.setName("Android");

    categoryRepository.save(category);

    // get existing category
    Long savedCategoryId = category.getId();
    assertNotNull(savedCategoryId);

    Category categoryFromDb = categoryRepository.findById(savedCategoryId).orElse(null);
    assertNotNull(categoryFromDb);

    // update existing category using save method
    categoryFromDb.setName("IOS");
    categoryRepository.save(categoryFromDb);

    // check if the category has updated
    Category updatedCategoryFromDb = categoryRepository.findById(savedCategoryId).orElse(null);
    assertNotNull(updatedCategoryFromDb);

    assertEquals(savedCategoryId, updatedCategoryFromDb.getId());
    assertEquals("IOS", updatedCategoryFromDb.getName());

  }


}
