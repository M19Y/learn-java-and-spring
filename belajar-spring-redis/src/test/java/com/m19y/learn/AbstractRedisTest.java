package com.m19y.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class AbstractRedisTest {

  @Autowired
  protected StringRedisTemplate template;

  @Autowired
  protected CacheManager cacheManager;
}
