package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingletonConceptTest {

  @Test
  void databaseTest() {

    Database database1 = Database.getInstance();
    Database database2 = Database.getInstance();

    Assertions.assertSame(database1, database2);
  }
}
