package com.m19y.learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;

@Slf4j
@Configuration
public class ListenerConfig {

  @Autowired
  private StringRedisTemplate template;

  @Bean(destroyMethod = "stop", initMethod = "start")
  public StreamMessageListenerContainer<
          String,
          ObjectRecord<String, Order>> orderContainer(RedisConnectionFactory connectionFactory){

    var options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
            .pollTimeout(Duration.ofSeconds(5))
            .targetType(Order.class)
            .build();

    return StreamMessageListenerContainer.create(connectionFactory, options);
  }

  @Bean
  public Subscription orderSubscription(
          StreamMessageListenerContainer<String,ObjectRecord<String, Order>> orderContainer,
          OrderListener orderListener){

    try {
      template.opsForStream().createGroup("orders", "my-order-group");
    }catch (Throwable t){
      log.info("Redis stream already created!");
    }

    var offset = StreamOffset.create("orders", ReadOffset.lastConsumed());
    var consumer = Consumer.from("my-order-group", "consumer-1");
    var readRequest = StreamMessageListenerContainer.StreamReadRequest.builder(offset)
            .consumer(consumer)
            .autoAcknowledge(true)
            .cancelOnError(throwable -> false)
            .errorHandler(throwable -> log.warn(throwable.getMessage()))
            .build();

    return orderContainer.register(readRequest, orderListener);
  }

  @Bean
  public RedisMessageListenerContainer messageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                CustomerListener listener){
    var container = new RedisMessageListenerContainer();

    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(listener, new ChannelTopic("customers"));

    return container;

  }
}
