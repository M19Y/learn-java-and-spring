package com.m19y.learn;

import com.m19y.learn.keyspace.Product;
import com.m19y.learn.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

@SpringBootTest
public class RepositoryTest extends AbstractRedisTest{

  @Autowired
  private ProductRepository repository;

  @Test
  void testRepository() {
    Product product = Product.builder()
            .id("1")
            .name("Laptop")
            .price(10_000_000L)
            .build();

    repository.save(product);

    Map<Object, Object> entries = template.opsForHash().entries("products:" + product.getId());

    assertEquals(product.getId(), entries.get("id"));
    assertEquals(product.getName(), entries.get("name"));
    assertEquals(product.getPrice().toString(), entries.get("price"));

    Product productFromDb = repository.findById("1").orElse(null);
    assertNotNull(productFromDb);

    assertEquals("1", productFromDb.getId());
    assertEquals("Laptop", productFromDb.getName());
    assertEquals(10_000_000L, productFromDb.getPrice());


  }
}
