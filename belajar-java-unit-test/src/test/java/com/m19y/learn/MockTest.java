package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockTest {

  @Test
  void normalList() {
    List<String> list = new ArrayList<>();
    // list akan throw error jika belum ada datanya
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> System.out.println(list.get(0)));
  }

  @Test
  void testMock() {
    List<String> list = Mockito.mock(List.class);

    // Mock akan selalu mengembalikan nilai null
    Assertions.assertNull(list.get(0));

    // menambahkan data ke list menggunakan mock
    Mockito.when(list.get(0)).thenReturn("Vro");

    Assertions.assertEquals("Vro", list.get(0));

    // kita bisa menambahkan diindex berapapun valuenya
    Mockito.when(list.get(100)).thenReturn("Sista");
    Assertions.assertEquals("Sista", list.get(100));

    Mockito.when(list.get(1)).thenReturn("Uncle");

    System.out.println(list.get(1));
    System.out.println(list.get(1));
    Mockito.verify(list, Mockito.times(2)).get(1);



  }
}

