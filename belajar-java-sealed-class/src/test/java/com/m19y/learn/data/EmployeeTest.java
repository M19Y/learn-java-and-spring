package com.m19y.learn.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

  @Test
  void testCreate() {
    Employee employee = new Employee();
  }

  @Test
  void testManager() {
    Manager manager = new Manager();
  }
}