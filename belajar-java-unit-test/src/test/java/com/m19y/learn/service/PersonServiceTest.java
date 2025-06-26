package com.m19y.learn.service;

import com.m19y.learn.data.Person;
import com.m19y.learn.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Extensions({
    @ExtendWith(MockitoExtension.class)
})
public class PersonServiceTest {

  @Mock
  private PersonRepository repository;

  private PersonService service;

  @BeforeEach
  void setUp() {
    service = new PersonService(repository);
  }

  @Test
  void personNotFoundTest() {

    IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      service.get("simple-not-found");
    });

    Assertions.assertEquals("Person not found", error.getMessage());
  }

  @Test
  void personWithMocking() {

    String id = "simple-person-id";
    Mockito.when(repository.selectById(id))
        .thenReturn(new Person(id, "Jamal"));

    Person person = service.get(id);
    Assertions.assertNotNull(person);

    Mockito.verify(repository, Mockito.times(1)).selectById("simple-person-id");
    Assertions.assertEquals("simple-person-id", person.getId());
    Assertions.assertEquals("Jamal", person.getName());
  }


  @Test
  void registerSuccessBadTest() {
    Person person = service.register("Vro");

    Assertions.assertNotNull(person);
    Assertions.assertNotNull(person.getId());
    Assertions.assertEquals("Vro", person.getName());
  }

  @Test
  void registerSuccessGoodTest() {
    Person person = service.register("Vro");

    Assertions.assertNotNull(person);
    Assertions.assertNotNull(person.getId());
    Assertions.assertEquals("Vro", person.getName());

    Mockito.verify(repository, Mockito.times(1))
        .insert(new Person(person.getId(), "Vro"));
  }
}
