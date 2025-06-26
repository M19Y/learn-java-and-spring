package com.m19y.learn;

import com.m19y.learn.model.polymorphism.Employee;
import com.m19y.learn.model.polymorphism.Manager;
import com.m19y.learn.model.polymorphism.VicePresident;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolymorphismTest {

  @Test
  void create() {

    Employee employee = new Employee("Bro");
    employee.sayHallo("Grandpa");

    employee = new Manager("Sis");
    employee.sayHallo("Grandma");

    employee = new VicePresident("Cousin");
    employee.sayHallo("Mother");
  }

  @Test
  void methodPolymorphism() {

    sayYooHoo(new Employee("Bro"));
    sayYooHoo(new Manager("Sis"));
    sayYooHoo(new VicePresident("Cousin"));

  }

  @Test
  void instanceOfTest() {
    sayRawr(new Employee("Bro"));
    sayRawr(new Manager("Sis"));
    sayRawr(new VicePresident("Shifu"));
  }

  @Test
  void errorCastObject() {

    saySimple(new VicePresident("Bro"));

    // error karena manager tidak bisa di cast langsung dengan VP
    Assertions.assertThrows(ClassCastException.class, () -> {
      saySimple(new Manager("Sis"));
    });
  }

  static void sayYooHoo(Employee employee){
    System.out.println("YooHoo " + employee.name);
  }

  static void sayRawr(Employee employee){
    if(employee instanceof VicePresident){
      VicePresident vp = (VicePresident) employee;
      System.out.println("Rawr Vp " + vp.name);
    } else if (employee instanceof Manager manager) { // using pattern variable
      System.out.println("Rawr Manager " + manager.name);
    }else{
      System.out.println("Rawr Employee " + employee.name);
    }
  }

  static void saySimple(Employee employee){
    VicePresident vp = (VicePresident) employee;
    System.out.println("Hai VP " + vp.name);
  }
}
