package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;

public class TestPath {

  @Test
  void simpleTest() throws URISyntaxException {

    Path path = Path.of(FormServlet.class.getResource("/html/form.html").toURI());
    System.out.println(path);
  }
}
