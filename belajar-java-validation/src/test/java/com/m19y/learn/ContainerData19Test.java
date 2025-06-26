package com.m19y.learn;

import com.m19y.learn.util.AbstractValidatorTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ContainerData19Test extends AbstractValidatorTest {

  @Test
  void testContainerDataOnListOfStrings() {

    Product product = new Product();
    product.setIngredients(new ArrayList<>());
    product.getIngredients().add("Sugar");
    product.getIngredients().add(" ");

    Order order = new Order();
    product.setOrders(List.of(order));
    viewViolation(product);
  }
}
