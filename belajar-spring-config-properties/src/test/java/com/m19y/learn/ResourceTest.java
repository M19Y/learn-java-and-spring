package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class ResourceTest {

  @Test
  void testGetFileInResource() throws IOException {
    Resource resource = new ClassPathResource("/text/simple.txt");

    Assertions.assertNotNull(resource);
    Assertions.assertTrue(resource.exists());
    Assertions.assertNotNull(resource.getFile());

  }
}
