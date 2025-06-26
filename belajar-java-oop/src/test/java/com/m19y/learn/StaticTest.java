package com.m19y.learn;

import com.m19y.learn.model.learnstatic.Application;
import com.m19y.learn.model.learnstatic.Constant;
import com.m19y.learn.model.learnstatic.Country;
import com.m19y.learn.model.learnstatic.MathUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StaticTest {

  @Test
  void staticFields() {

    System.out.println(Constant.APPLICATION);
    System.out.println(Constant.VERSION);
  }

  @Test
  void staticMethods() {
    Assertions.assertEquals(55, MathUtil.sum(1,2,3,4,5,6,7,8,9,10));
  }

  @Test
  void staticInnerClass() {
    Country.City city = new Country.City();
    city.setName("New York");
    city.setPopulation(100);

    System.out.println(city.getName());
    System.out.println(city.getPopulations());
  }

  @Test
  void staticBlocks() {
    System.out.println(Application.PROCESSORS);
  }


}
