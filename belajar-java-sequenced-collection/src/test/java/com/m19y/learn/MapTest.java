package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.SequencedMap;
import java.util.TreeMap;

public class MapTest {


  @Test
  void mapTest() {

    SequencedMap<String, String> people = new TreeMap<>();

    people.put("c", "Unc");
    people.put("a", "Aunty");
    people.put("b", "Grandma");

    Assertions.assertEquals("Unc", people.lastEntry().getValue());
    Assertions.assertEquals("Aunty", people.firstEntry().getValue());


    // reversed
    SequencedMap<String, String> reversed = people.reversed();
    Assertions.assertEquals("Aunty", reversed.lastEntry().getValue());
    Assertions.assertEquals("Unc", reversed.firstEntry().getValue());

    Assertions.assertThrows(UnsupportedOperationException.class, () -> people.putFirst("d", "wrong"));
  }


  @Test
  void linkedHashMap() {
    SequencedMap<String, String> family = new LinkedHashMap<>();

    // saat menggunakan linked hash map (putFirst, putLast) menjadi berguna
    family.putFirst("father", "Elon Musk");
    family.putFirst("mother", "Mrs.Musk");
    family.putFirst("first-child", "x");

    Assertions.assertEquals("x", family.pollFirstEntry().getValue());
    Assertions.assertEquals("Elon Musk", family.pollLastEntry().getValue());
  }
}
