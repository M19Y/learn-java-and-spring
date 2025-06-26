package com.m19y.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

@Slf4j
@SpringBootTest
public class PubSubTest {

  @Autowired
  private StringRedisTemplate template;

  @Test
  void pubSubTest() throws InterruptedException {

    template.getConnectionFactory().getConnection().subscribe(new MessageListener() {
      @Override
      public void onMessage(Message message, byte[] pattern) {
        String event = new String(message.getBody());
        log.info("Receive Message : {}", event);
      }
    }, "my-channel".getBytes());

    for (int i = 0; i < 10; i++) {
      template.convertAndSend("my-channel", "Hello world " + i);
    }

    Thread.sleep(Duration.ofSeconds(10L));
  }
}
