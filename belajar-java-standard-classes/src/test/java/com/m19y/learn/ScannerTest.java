package com.m19y.learn;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ScannerTest {

  public static void main(String[] args) {

    // Scanner tidak bisa diggunakan di test, karena test cuman read only
    Scanner scanner = new Scanner(System.in);

    System.out.print("String input: ");
    String strInput = scanner.nextLine();
    System.out.println(strInput);

    System.out.print("\nInteger input: ");
    Integer intInput = scanner.nextInt();
    System.out.println(intInput);

    System.out.print("\nDouble input: ");
    Double doubleInput = scanner.nextDouble();
    System.out.println(doubleInput);

    System.out.print("\nChar input: ");
    Character charInput = scanner.next().charAt(0);
    System.out.println(charInput);
    //... Long, Float ect...
  }
}
