package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class MerchantTest {

  @Test
  void testRequiredArgsConstructor() {
    Class<Merchant> merchantClass = Merchant.class;
    Assertions.assertEquals(1, merchantClass.getDeclaredConstructors().length);
  }
}