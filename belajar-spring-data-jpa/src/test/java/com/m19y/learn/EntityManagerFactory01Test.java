package com.m19y.learn;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityManagerFactory01Test {

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  @Test
  void testEntityMangerFactory() {

    Assertions.assertNotNull(entityManagerFactory);

    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Assertions.assertNotNull(entityManager);
    entityManager.clear();
  }
}

