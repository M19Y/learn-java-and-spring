package com.m19y.learn;

import com.m19y.learn.entity.Category;
import com.m19y.learn.repository.CategoryRepository;
import com.m19y.learn.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryService03Test {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private CategoryService categoryService;

  @BeforeEach
  void setUp() {
    categoryRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    categoryRepository.deleteAll();
  }

  @Test
  void AOPShouldActive_whenTransactionalCalledInDifferentClass() {
    Assertions.assertThrows(RuntimeException.class, () -> {
      categoryService.create();
    });

    // transactional will be activated and will not save any data in database;
    List<Category> categories = categoryRepository.findAll();
    Assertions.assertTrue(categories.isEmpty());
  }

  @Test
  void AOPShouldNotActive_whenTransactionalCalledInSameClassWithDifferentMethod() {
    Assertions.assertThrows(RuntimeException.class, () -> {
      categoryService.test();
    });

    // transactional will be not activated and will save data in a database
    // event though using transactional
    List<Category> categories = categoryRepository.findAll();
    Assertions.assertFalse(categories.isEmpty());
    Assertions.assertEquals(5, categories.size());
  }

  @Test
  void programmaticTransactionalShouldThrowError_whenTransactionalFailed() {
    Assertions.assertThrows(RuntimeException.class, () -> {
      categoryService.createCategoriesTransactional();
    });

    // manual transactional will be activated and will not save any data in database;
    List<Category> categories = categoryRepository.findAll();
    Assertions.assertTrue(categories.isEmpty());
  }

  @Test
  void programmaticTransactionalShouldThrowError_whenTransactionalFailedCalledInDifferentMethodInSameClass() {
    Assertions.assertThrows(RuntimeException.class, () -> {
      categoryService.test2();
    });

    // manual transactional will be activated even it called in a different method
    // and will not save any data in a database;
    List<Category> categories = categoryRepository.findAll();
    Assertions.assertTrue(categories.isEmpty());
  }

  @Test
  void manualTransactionalShouldThrowError_whenTransactionalFailed() {
    Assertions.assertThrows(RuntimeException.class, () -> {
      categoryService.createCategoriesManualTransactional();
    });

    // manual transactional will be activated and will not save any data in database;
    List<Category> categories = categoryRepository.findAll();
    Assertions.assertTrue(categories.isEmpty());
  }

  @Test
  void manualTransactionalShouldThrowError_whenTransactionalFailedCalledInDifferentMethodInSameClass() {
    Assertions.assertThrows(RuntimeException.class, () -> {
      categoryService.test3();
    });

    // manual transactional will be activated even it called in a different method
    // and will not save any data in a database;
    List<Category> categories = categoryRepository.findAll();
    Assertions.assertTrue(categories.isEmpty());
  }
}
