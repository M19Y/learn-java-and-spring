package com.m19y.learn;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.*;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@SpringBootTest
public class SetTest {

  private final StringRedisTemplate template;

  @Autowired
  public SetTest(StringRedisTemplate template) {
    this.template = template;
  }


  @Test
  void valueOperationsSetTest() throws InterruptedException {

    SetOperations<String, String> operation = template.opsForSet();
    operation.add("students", "Bro");
    operation.add("students", "Sis");
    operation.add("students", "Sis");
    operation.add("students", "Sis");
    operation.add("students", "Unc");
    operation.add("students", "Unc");
    operation.add("students", "Aunty");

    assertEquals(4, operation.size("students"));

    Set<String> students = operation.members("students");

    assertNotNull(students);

    students.forEach(System.out::println);

    assertThat(students, hasItems("Unc", "Bro", "Sis", "Aunty"));
  }

  // shorted set
  @Test
  void zSetTest() {

    ZSetOperations<String, String> operations = template.opsForZSet();

    operations.add("score", "Son Goku", 94);
    operations.add("score", "Son Gohan", 78);
    operations.add("score", "Picolo", 99);
    operations.add("score", "Crilin", 55);

    assertEquals(4, operations.size("score"));

    assertEquals("Picolo", operations.popMax("score").getValue());
    assertEquals("Son Goku", operations.popMax("score").getValue());
    assertEquals("Crilin", operations.popMin("score").getValue());
    assertEquals("Son Gohan", operations.popMin("score").getValue());

    assertEquals(0, operations.size("score"));

  }


  @Test
  void hashTest() {
    HashOperations<String, String, String> operations = template.opsForHash();

    // one by one
    /*
    operation.put("user:1", "id", "123");
    operations.put("user:1", "name", "Bro");
    operations.put("user:1", "age", "23");
    operations.put("user:1", "email", "bro@gmail.com");*/


    // using a map
    Map<String, String> user1HashKeyValue = Map.of(
            "id", "123",
            "age", "23",
            "name", "Bro",
            "email", "bro@gmail.com"
    );
    operations.putAll("user:1", user1HashKeyValue);

    assertEquals(4, operations.size("user:1"));

    assertEquals("123", operations.get("user:1", "id"));
    assertEquals("Bro", operations.get("user:1", "name"));

    String userAge = operations.get("user:1", "age");
    assertNotNull(userAge);
    int age = Integer.parseInt(userAge);
    assertEquals(23, age);

    assertEquals("bro@gmail.com", operations.get("user:1", "email"));
  }

  @Test
  void geoTest() {

    GeoOperations<String, String> operations = template.opsForGeo();

    operations.add("house", new Point(122.461740,-3.968617), "Tsar");
    operations.add("house", new Point(122.508438, -3.999351), "Sil");
    Distance distance = operations.distance("house", "Tsar", "Sil", Metrics.KILOMETERS);

    assertEquals(6.2079, distance.getValue());

    GeoResults<RedisGeoCommands.GeoLocation<String>> school = operations.search("house", new Circle(
            new Point(122.504262, -3.987473),
            new Distance(6, Metrics.KILOMETERS)
    ));

    assertEquals(2, school.getContent().size());

    assertEquals("Sil", school.getContent().get(0).getContent().getName());
    assertEquals("Tsar", school.getContent().get(1).getContent().getName());

  }

  @Test
  void hyperLogLog() {
    // tidak bisa mengambil datanya, hanya bisa mengambil jumlah total unique value-nya
    HyperLogLogOperations<String, String> operations = template.opsForHyperLogLog();

    operations.add("traffics", "bro", "sis", "unc");
    operations.add("traffics", "unc", "aunty", "bro");
    operations.add("traffics", "grandpa", "grandma", "sis");

    // data unique-nya -> bro, sis, unc, aunty, grandpa, grandma = 6
    assertEquals(6L, operations.size("traffics"));
  }

  @Test
  void transactionTest() {

    template.execute(new SessionCallback<Object>() {
      @Override
      public Object execute(RedisOperations operations) throws DataAccessException {
        operations.multi(); // start transaction with 1 same operation

        template.opsForValue().set("trans-test1", "Bro", Duration.ofSeconds(2));
        template.opsForValue().set("trans-test2", "Sis", Duration.ofSeconds(2));

        // if u try to commnet this .exec(), the transactions will not be safe
        operations.exec(); // execute all those transaction operations
        return null;
      }
    });

    assertEquals("Bro", template.opsForValue().get("trans-test1"));
    assertEquals("Sis", template.opsForValue().get("trans-test2"));
  }

  @Test
  void pipelineTest() {

    List<Object> statuses = template.executePipelined(new SessionCallback<Object>() {
      @Override
      public Object execute(RedisOperations operations) throws DataAccessException {
        operations.opsForValue().set("test1", "Bro1", Duration.ofSeconds(2));
        operations.opsForValue().set("test2", "Bro2", Duration.ofSeconds(2));
        operations.opsForValue().set("test3", "Bro3", Duration.ofSeconds(2));
        operations.opsForValue().set("test4", "Bro4", Duration.ofSeconds(2));
        return null;
      }
    });

    assertThat(statuses, hasSize(4));
    assertThat(statuses, hasItems(true));
    assertThat(statuses, not(hasItems(false)));
  }

  @Test
  void publishStreamTest() {

    StreamOperations<String, Object, Object> operations = template.opsForStream();
    MapRecord<String, String, String> record = MapRecord.create("stream1",
            Map.of(
                    "name", "Bro sis",
                    "country", "USA"
            ));

    for (int i = 0; i < 10; i++) {
      operations.add(record);
    }
  }

  @Test
  void subscribeStreamTest() {
    StreamOperations<String, Object, Object> operations = template.opsForStream();

    MapRecord<String, String, String> addRecord = MapRecord.create("stream1",
            Map.of(
                    "name", "Bro unc",
                    "country", "USA"
            ));

    for (int i = 0; i < 10; i++) {
      operations.add(addRecord);
    }

    try{
      operations.createGroup("stream1", "simple-group");
    }catch (RedisSystemException e){
      log.info("group already exists!");
    }

    List<MapRecord<String, Object, Object>> records = operations.read(
            Consumer.from("simple-group", "sample-name"),
            StreamOffset.create("stream1", ReadOffset.lastConsumed()));

    for (MapRecord<String, Object, Object> record : records) {
      log.info("data => {}", record);
    }
  }
}
