package com.m19y.learn;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectMapperTest {

  @Test
  void create() {
    ObjectMapper objectMapper = new ObjectMapper();
    Assertions.assertNotNull(objectMapper);
  }
}
