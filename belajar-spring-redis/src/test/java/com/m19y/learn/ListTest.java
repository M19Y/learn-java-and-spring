package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

@SpringBootTest
public class ListTest {

  private final StringRedisTemplate template;

  @Autowired
  public ListTest(StringRedisTemplate template) {
    this.template = template;
  }


  @Test
  void valueOperationsListTest() throws InterruptedException {

    ListOperations<String, String> listOperations = template.opsForList();
    listOperations.rightPush("names", "Bro");
    listOperations.rightPush("names", "Sis");
    listOperations.rightPush("names", "Unc");
    listOperations.rightPush("names", "Aunty");

    Assertions.assertEquals(4, listOperations.size("names"));

    Assertions.assertEquals("Bro", listOperations.leftPop("names"));
    Assertions.assertEquals("Sis", listOperations.leftPop("names"));
    Assertions.assertEquals("Unc", listOperations.leftPop("names"));
    Assertions.assertEquals("Aunty", listOperations.leftPop("names"));

    Assertions.assertEquals(0, listOperations.size("names"));
  }
}
