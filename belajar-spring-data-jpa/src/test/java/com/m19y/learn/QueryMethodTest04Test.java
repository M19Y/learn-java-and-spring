package com.m19y.learn;

import com.m19y.learn.entity.Category;
import com.m19y.learn.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class QueryMethodTest04Test {

  @Autowired
  private CategoryRepository categoryRepository;


  @BeforeEach
  void setUp() {
    categoryRepository.deleteAll();
    Category category1 = new Category();
    category1.setName("Family");

    Category category2 = new Category();
    category2.setName("Neighbour");

    Category category3 = new Category();
    category3.setName("Village");

    Category category4 = new Category();
    category4.setName("Party");

    Category category5 = new Category();
    category5.setName("Hometown");

    Category category6 = new Category();
    category6.setName("Hometown 2");

    categoryRepository.saveAll(
      List.of(category1,
        category2,
        category3,
        category4,
        category5,
        category6
      )
    );
  }

  @AfterEach
  void tearDown() {
    categoryRepository.deleteAll();
  }

  @Test
  void shouldReturnOneCategory_whenFindFirstByName() {
    Category category = categoryRepository.findFirstByName("Hometown").orElse(null);
    assertNotNull(category);

    assertNotNull(category.getId());
    assertEquals("Hometown", category.getName());
  }

  @Test
  void shouldReturn3ListOfCategories_whenFindAllByNameLikeA() {
    List<Category> listCategoryHasCharA = categoryRepository.findAllByNameLike("%a%");

    assertFalse(listCategoryHasCharA.isEmpty());
    assertEquals(3, listCategoryHasCharA.size());

    List<String> names = listCategoryHasCharA.stream().map(Category::getName).toList();
    assertThat(names, containsInAnyOrder("Village", "Party", "Family"));
  }
}
