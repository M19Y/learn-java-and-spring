package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class CollectionToSynchronizedTest {

  @Test
  void testConversion() {
    List<String> names = List.of("Son goku", "Son gohan", "Pickolo"); // tidak aman di akses multiple thread
    List<String> syncNames = Collections.synchronizedList(names); // aman untuk di akses oleh multiple thread
  }
}
