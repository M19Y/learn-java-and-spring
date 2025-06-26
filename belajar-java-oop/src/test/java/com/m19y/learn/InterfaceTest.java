package com.m19y.learn;

import com.m19y.learn.model.Avanza;
import com.m19y.learn.model.Car;
import com.m19y.learn.model.IsMaintenance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InterfaceTest {

  @Test
  void create() {

    Car car = new Avanza();

    // must override method
    car.drive();
    Assertions.assertEquals(4, car.getTire());
    // interface can extends another interface by using (extends key)
    Assertions.assertEquals("Toyota", car.getBrand());

    // default method
    Assertions.assertFalse(car.isBig());

    // public static final field in interface
    Assertions.assertEquals(10, IsMaintenance.MAX_TOTAL_MAINTENANCE);
    Assertions.assertEquals(2, IsMaintenance.MIN_TOTAL_MAINTENANCE);
  }
}
