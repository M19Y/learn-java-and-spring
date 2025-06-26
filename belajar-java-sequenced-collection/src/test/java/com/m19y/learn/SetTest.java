package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.SequencedSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

  @Test
  void setTest() {

    SequencedSet<String> names = new TreeSet<>();

    names.add("Unc");
    names.add("Bro");
    names.add("Sis");

    Assertions.assertEquals("Bro", names.getFirst());
    Assertions.assertEquals("Unc", names.getLast());

    SequencedSet<String> reversed = names.reversed();

    Assertions.assertEquals("Unc", reversed.getFirst());
    Assertions.assertEquals("Bro", reversed.getLast());

    String first = names.removeFirst();
    Assertions.assertEquals("Bro", first);
    String second = names.removeFirst();
    Assertions.assertEquals("Sis", second);

    Assertions.assertEquals(1, names.size());
  }
}
