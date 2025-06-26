package com.m19y.learn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisMap;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.data.redis.support.collections.RedisSet;
import org.springframework.data.redis.support.collections.RedisZSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CollectionTest {

  @Autowired
  private StringRedisTemplate template;

  @Test
  void testList() {

    // ini akan otomatis di tambahakan ke redis
    List<String> names = RedisList.create("names", template);
    names.add("Bro");
    names.add("Sis");
    names.add("uncle");
    names.add("aunty");

    List<String> namesFromDb = template.opsForList().range("names", 0, -1);

    assertNotNull(namesFromDb);
    assertEquals(names.size(), namesFromDb.size());
    assertThat(namesFromDb, hasItems("Bro", "Sis", "uncle", "aunty"));

  }

  @Test
  void testSet() {

    Set<String> traffic = RedisSet.create("traffic", template);
    traffic.add("bro");
    traffic.add("sis");
    traffic.add("unc");
    traffic.add("aunty");

    traffic.addAll(Set.of("grandpa", "grandma", "aunty"));
    traffic.addAll(Set.of("grandson", "bro", "unc"));

    Set<String> trafficFromDb = template.opsForSet().members("traffic");
    assertNotNull(trafficFromDb);

    assertEquals(7, trafficFromDb.size());

    assertThat(trafficFromDb, hasItems("bro", "sis", "unc", "aunty", "grandpa", "grandma", "grandson"));
  }

  @Test
  void testZSet() {

    RedisZSet<String> set = RedisZSet.create("winner", template);
    set.add("Bro", 100);
    set.add("sis", 60);
    set.add("unc", 70);
    set.add("grandpa", 80);
    set.add("aunty", 90);

    assertThat(set, hasItems("Bro", "sis", "grandpa", "aunty", "unc"));

    Set<String> winnerFromDb = template.opsForZSet().range("winner", 0, -1);

    assertNotNull(winnerFromDb);
    assertEquals(5, winnerFromDb.size());
    assertThat(winnerFromDb, hasItems("Bro", "sis", "grandpa", "aunty", "unc"));

    assertEquals("sis", set.popFirst());
    assertEquals("unc", set.popFirst());
    assertEquals("grandpa", set.popFirst());
    assertEquals("aunty", set.popFirst());
    assertEquals("Bro", set.popFirst());

  }

  @Test
  void testMap() {

    Map<String, String> users = new DefaultRedisMap<>("user:1", template);

    users.put("name", "Bro");
    users.put("address", "New York");

    assertThat(users, hasEntry("name", "Bro"));
    assertThat(users, hasEntry("address", "New York"));

    Map<Object, Object> usersFromDb = template.opsForHash().entries("user:1");

    assertThat(usersFromDb, hasEntry("name", "Bro"));
    assertThat(usersFromDb, hasEntry("address", "New York"));

  }
}
