package com.m19y.learn;

import com.m19y.learn.model.Animal;
import com.m19y.learn.model.Cat;
import com.m19y.learn.model.City;
import com.m19y.learn.model.Location;
import org.junit.jupiter.api.Test;

public class AbstractTest {

  @Test
  void create() {

    // Location location = new Location(); // Error (cannot be instantiated)
    Location location = new City();
    location.name = "Jakarta";
    System.out.println(location.name);

  }

  @Test
  void abstractMethod() {

    Animal cat = new Cat();
    cat.name = "Bro";
    cat.run();

  }

  @Test
  void concreteMethod() {
    Animal cat = new Cat();
    cat.name = "Tom";

    // optional override method
    cat.sound("Meow");
  }
}
