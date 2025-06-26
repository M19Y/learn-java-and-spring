package com.m19y.learn;

import com.m19y.learn.data.Employee;
import com.m19y.learn.data.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SuperClassTest {

  @Test
  void testGetSuperClass() {

    Class<Manager> managerClass = Manager.class;
    Class<? super Manager> superclassManager = managerClass.getSuperclass();
    Class<? super Manager> superclassEmployee = superclassManager.getSuperclass();
    Class<? super Manager> superclassObject = superclassEmployee.getSuperclass();

    Assertions.assertInstanceOf(Employee.class, new Manager());
    Assertions.assertSame(Employee.class, superclassManager); // com.m19y.learn.data.Employee
    Assertions.assertSame(Object.class, superclassEmployee); // java.lang.Object
    Assertions.assertNull(superclassObject); // null

    System.out.println(superclassManager);
    System.out.println(superclassEmployee);
    System.out.println(superclassObject);

  }

  @Test
  void loopTheSuperClass() {

    Class<? super Manager> temp = Manager.class;
    do {
      temp = temp.getSuperclass();
      System.out.println(temp);
    } while (temp != null);

  }
}
