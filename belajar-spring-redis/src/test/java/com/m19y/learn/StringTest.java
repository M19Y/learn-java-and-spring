package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

@SpringBootTest
public class StringTest {

  private final StringRedisTemplate template;

  @Autowired
  public StringTest(StringRedisTemplate template) {
    this.template = template;
  }

  @Test
  void testTemplate() {
    Assertions.assertNotNull(template);
  }

  @Test
  void valueOperationsStringTest() throws InterruptedException {

    ValueOperations<String, String> operations = template.opsForValue();

    operations.set("name", "Bro", Duration.ofSeconds(2));
    Assertions.assertEquals("Bro", operations.get("name"));

    Thread.sleep(Duration.ofSeconds(3));

    Assertions.assertNull(operations.get("name"));

  }
}
