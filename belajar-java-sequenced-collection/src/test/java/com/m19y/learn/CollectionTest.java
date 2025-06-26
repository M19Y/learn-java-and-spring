package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

public class CollectionTest {

  @Test
  void collectionTest() {
    SequencedCollection<String> names = new ArrayList<>();
    names.addLast("Bro");
    names.addFirst("Sis");
    names.addFirst("Unc");

    Assertions.assertEquals(List.of("Unc", "Sis", "Bro"), names);

    // first and last method
    Assertions.assertEquals("Unc", names.getFirst());
    Assertions.assertEquals("Bro", names.getLast());

    // reverse array method
    Assertions.assertEquals(List.of("Bro", "Sis", "Unc"), names.reversed());

    // remove and receive
    String firstName = names.removeFirst();
    String lastName = names.removeLast();

    Assertions.assertEquals("Unc", firstName);
    Assertions.assertEquals("Bro", lastName);
    Assertions.assertEquals(1, names.size()); // sisa 1

  }
}
