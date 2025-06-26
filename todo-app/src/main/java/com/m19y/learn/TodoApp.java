package com.m19y.learn;

import java.util.Scanner;

public class TodoApp {

  public static String[] model = new String[10];
  public static java.util.Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) {

    viewShowTodoList();
//    testShowTodoList();
//    testAddTodoList();
//    testRemove();
//    testInput();
//    testViewShowTodoList();
//    testViewAddTodoList();
//    testViewRemoveTodoList();
  }


  // SERVICE ===================================

  /*
  * logika bisnis menampilkan todo list
  */
  public static void showTodoList(){
    System.out.println("TODOLIST");
    for (int i = 0; i < model.length; i++) {

      var todo = model[i];
      var no = i + 1;
      if (todo != null){
        System.out.println(no + ". " + todo);
      }
    }
  }

  public static void testShowTodoList(){
    model[0] =  "Simple todo 1";
    model[1] =  "Simple todo 2";

    showTodoList();
  }

  /*
  * logika bisnis menambahkan todo list
  */
  public static void addTodoList(String todo){

    // cek apakah array model sudah penuh?
    boolean isFull = true;
    for (int i = 0; i < model.length; i++) {
      if(model[i] == null){
        isFull = false;
        break;
      }
    }

    // jika arraynya sudah penuh maka kita akan resize arranya 2x lipat
    if(isFull){
      String[] temp = model;
      model = new String[model.length * 2];

      for (int i = 0; i < temp.length; i++) {
        model[i] = temp[i];
      }
    }

    // tambahkan data ke array yang masih null
    for (int i = 0; i < model.length; i++) {
      if(model[i] == null){
        model[i] = todo;
        break;
      }
    }
  }

  public static void testAddTodoList(){
    for (int i = 1; i <= 25; i++) {
      addTodoList("Simple add todo : " + i);
    }

    showTodoList();

    // model.lengthnya seharusnya 40
    if(model.length == 40){
      System.out.println("Total panjang array adalah 40, Success");
    }else {
      System.err.println("Ada yang salah " + model.length);
    }
  }

  /*
   * logika bisnis menghapus todo list
   */
  public static boolean removeTodoList(Integer number){

    if((number - 1 ) >= model.length){
      // cek apakah nilai yang dimasukan lebih besar daripada panjang array, jika iya maka false
      return false;
    }else if(model[number - 1] == null){
      // cek apakah todonya yang dihapus sudah null?, jika sudah maka false
      return false;
    }else{
      // jika number yang dimasukan tidak lebih besar dari pada panjang array
      // dan jika number pada model yang dihapus bukan dalam keadaan null

      for (int i = (number - 1); i < model.length; i++) {

        if(i == (model.length - 1)){
          // jika i sama dengan panjang array dikurangi satu (sudah array paling ujung) maka langsung di nullkan
          model[i] = null;

        }else{
          // tapi apabila arraynya tidak sama dengan panjang array di kurangi satu maka kita harus mengeser array setelah number i (i + 1)
          model[i] = model[i + 1];
        }
      }
      return true;
    }
  }

  public static void testRemove(){
    addTodoList("simple 1");
    addTodoList("simple 2");
    addTodoList("simple 3");
    addTodoList("simple 4");

    System.out.println("Harus false => " + removeTodoList(20)); // number yang dimasukan berlebihan
    System.out.println("Harus false => " + removeTodoList(5)); // number yang dimasukan sudah null
    System.out.println("Harus true  => " + removeTodoList(3));

    showTodoList();

  }

  public static String input(String info){
    System.out.print(info + " : ");
    return  scanner.nextLine();
  }

  public static void testInput(){

    System.out.println(input("Nama"));
    System.out.println(input("Company"));
  }
  // VIEW ===================================

  /*
  * Menampilkan view todo list
  */
  public static void viewShowTodoList(){

    while(true){
      showTodoList();
      System.out.println("\n====>> Menu <<====");
      System.out.println("1. Tambah");
      System.out.println("2. Hapus");
      System.out.println("X. Keluar");

      String userInput = input("Pilih");
      if(userInput.equals("1") || userInput.equalsIgnoreCase("Tambah")){
        viewAddTodoList();
      }else if(userInput.equals("2") || userInput.equalsIgnoreCase("Hapus")){
        viewRemoveTodoList();
      } else if (userInput.equalsIgnoreCase("X") || userInput.equalsIgnoreCase("Keluar") ) {
        break;
      }else{
        System.err.println("Pilihan anda tidak dimengerti!");
      }
    }
  }

  public static void testViewShowTodoList(){
    addTodoList("Simple 1");
    addTodoList("Simple 2");
    addTodoList("Simple 3");
    addTodoList("Simple 4");
    addTodoList("Simple 5");

    viewShowTodoList();
  }
  /*
   * Menampilkan view add todo list
   */
  public static void viewAddTodoList(){
    System.out.println("Menambah TODO");
    String todo = input("Todo (x Jika Batal)");

    if(todo.equals("x")){
      /* do nothing */
    }else{
      addTodoList(todo);
    }
  }

  public static void testViewAddTodoList(){
    addTodoList("Satu");
    addTodoList("Dua");
    addTodoList("Tiga");

    viewAddTodoList();
    showTodoList();
  }

  /*
   * Menampilkan view remove todo list
   */
  public static void viewRemoveTodoList(){
    System.out.println("MENGHAPUS TODO");
    String number = input("Nomor yang di hapus (x Jika Batal)");

    if(number.equals("x")) {
      /* do nothing */
    }else{
      try{
        boolean isSuccess = removeTodoList(Integer.valueOf(number));
        if(!isSuccess) System.err.println("Gagal menghapus todo list nomor " + number);
      }catch (NumberFormatException e){
        System.err.println("Silahkan Pilih angka!");
      }
    }
  }

  public static void testViewRemoveTodoList(){
    addTodoList("Satu");
    addTodoList("Dua");
    addTodoList("Tiga");

    viewRemoveTodoList();
    showTodoList();
  }
}
