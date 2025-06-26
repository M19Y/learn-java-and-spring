package com.m19y.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectStreamTest {


  @Test
  void savePerson() {

    Person person = new Person();
    person.setId("1");
    person.setName("Simple name");

    Path path = Path.of("simple.person");
    try (OutputStream stream = Files.newOutputStream(path)) {
      ObjectOutputStream outputStream = new ObjectOutputStream(stream);

      outputStream.writeObject(person);
      outputStream.flush();
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void readPerson() {
    Path path = Path.of("simple.person");

    try (InputStream stream = Files.newInputStream(path)) {
      ObjectInputStream inputStream = new ObjectInputStream(stream);
      Object objectStream = inputStream.readObject();
      Person person = (Person) objectStream;

      Assertions.assertInstanceOf(Person.class, objectStream);
      Assertions.assertEquals("Simple name", person.getName());
      Assertions.assertEquals("1", person.getId());

    } catch (IOException | ClassNotFoundException e) {
      Assertions.fail(e);
    }

  }

  @Test
  void testCollectionWrite() {

    List<String> list = List.of("Ajib", "Apon", "Abilal", "Adam", "Jait", "Patih");

    try (OutputStream stream = Files.newOutputStream(Path.of("names.list"))) {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
      objectOutputStream.writeObject(list);
      objectOutputStream.flush();
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void testCollectionReadStr() {

    try (InputStream stream = Files.newInputStream(Path.of("names.list"))) {
      ObjectInputStream inputStream = new ObjectInputStream(stream);
      Object object = inputStream.readObject();
      List<String> list = ((List<?>) object).stream()
//            .filter(data -> String.class.isInstance(data))
//            .map(data -> String.class.cast(data))
          .filter(String.class::isInstance) // Pastikan elemen adalah String
          .map(String.class::cast)
          .toList();
      list.forEach(System.out::println);

    } catch (IOException | ClassNotFoundException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void testCollectionWriteInt() {

    List<Integer> list = List.of(1, 3, 5, 7, 9);

    try (OutputStream stream = Files.newOutputStream(Path.of("numbers.list"))) {
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
      objectOutputStream.writeObject(list);
      objectOutputStream.flush();
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void testCollectionReadInt() {

    try (InputStream stream = Files.newInputStream(Path.of("numbers.list"))) {
      ObjectInputStream inputStream = new ObjectInputStream(stream);
      Object object = inputStream.readObject();
      List<Integer> list = ((List<?>) object).stream()
          .filter(Integer.class::isInstance) // Pastikan elemen adalah Integer
          .map(Integer.class::cast)
          .toList();
      list.forEach(System.out::println);

    } catch (IOException | ClassNotFoundException e) {
      Assertions.fail(e);
    }
  }
}
