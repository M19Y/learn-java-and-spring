package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

  @Test
  void testThrowErrorNullPointerAtConstructor() {
    assertThrows(NullPointerException.class, () -> {
      Member member = new Member(null, null);
    });
  }

  @Test
  void testThrowErrorNullPointerAtSetter() {
    assertThrows(NullPointerException.class, () -> {
      Member member = new Member("bri", "bro");
      member.setId(null);
      member.setName(null);
    });
  }

  @Test
  void testSayHelloNullPointerAtParameter() {

    assertThrows(NullPointerException.class, () -> {
      Member bro = new Member("1", "Bro");
      bro.sayHello(null);
    });
  }
}