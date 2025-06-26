package com.m19y.learn.keyspace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@KeySpace("products")
public class Product implements Serializable {

  @Id
  private String id;
  private String name;
  private Long price;

  // kapan harus di hapus kalau ttl (lebih kecil sama dengan dari pada) 0 itu berarti tidak akan pernah di hapus
  @TimeToLive(unit = TimeUnit.SECONDS)
  private Long ttl = -1L;

}
