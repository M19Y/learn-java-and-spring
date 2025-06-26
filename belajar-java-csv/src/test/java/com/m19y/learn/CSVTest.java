package com.m19y.learn;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVTest {

  @Test
  void create() {

    try (Writer writer = Files.newBufferedWriter(Path.of("sample.csv"));
         CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

      printer.printRecord("Abilal", "Bin Udin", 77);
      printer.printRecord("Ajib", "Bin Ajaib", 66);
      printer.printRecord("Apon", "Bin Mail", 99);

    } catch (IOException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void read() {
    try (Reader reader = Files.newBufferedReader(Path.of("sample.csv"));
         CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT)) {

      for (CSVRecord record : parser) {
        System.out.println("First name: " + record.get(0));
        System.out.println("Last name: " + record.get(1));
        System.out.println("Score: " + record.get(2));
        System.out.print("\n");
      }
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void createWithHeader() {

    try (Writer writer = Files.newBufferedWriter(Path.of("sample-header.csv"));
         CSVPrinter printer = new CSVPrinter(
                 writer,
                 CSVFormat.DEFAULT.builder().setHeader("First Name", "Last Name", "Score").build())) {

      printer.printRecord("Abilal", "Bin Udin", 77);
      printer.printRecord("Ajib", "Bin Ajaib", 66);
      printer.printRecord("Apon", "Bin Mail", 99);

    } catch (IOException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void readWithHeader() {

    try (Reader reader = Files.newBufferedReader(Path.of("sample-header.csv"));
         CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.builder().setHeader().build())) {

      for (CSVRecord record : parser) {
        System.out.println("First name: " + record.get("First Name"));
        System.out.println("Last name: " + record.get("Last Name"));
        System.out.println("Score: " + record.get("Score"));
        System.out.print("\n");
      }
    } catch (IOException e) {
      Assertions.fail(e);
    }
  }

  @Test
  void createWithFormat() {

    try (Writer writer = Files.newBufferedWriter(Path.of("sample-header.csv"));
         CSVPrinter printer = new CSVPrinter(
                 writer,
                 CSVFormat.TDF.builder().setHeader("First Name", "Last Name", "Score").build())) {

      printer.printRecord("Abilal", "Bin Udin", 77);
      printer.printRecord("Ajib", "Bin Ajaib", 66);
      printer.printRecord("Apon", "Bin Mail", 99);

    } catch (IOException e) {
      Assertions.fail(e);
    }
  }


}
