package com.m19y.learn;

import com.m19y.learn.keyspace.Product;
import com.m19y.learn.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TimeToLiveTest extends AbstractRedisTest{

  @Autowired
  private ProductRepository productRepository;


  @Test
  void ttl_ProductShouldNotFound_After3Seconds() throws InterruptedException {
    Product product = Product.builder()
            .id("2")
            .name("Fish")
            .price(20_000L)
            .ttl(3L)
            .build();

    productRepository.save(product);

    assertTrue(productRepository.findById("2").isPresent());

    Thread.sleep(Duration.ofSeconds(5));

    assertFalse(productRepository.findById("2").isPresent());
  }
}
