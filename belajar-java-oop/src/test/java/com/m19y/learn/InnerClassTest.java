package com.m19y.learn;

import com.m19y.learn.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InnerClassTest {

  @Test
  void create() {

    Company company = new Company();
    company.setName("Fang");

    Company.Employee employee = company.new Employee();
    employee.setName("Vro");

    System.out.println(company.getName());
    System.out.println(employee.getName());
    Assertions.assertSame(company.getName(), employee.getCompany());
  }
}
