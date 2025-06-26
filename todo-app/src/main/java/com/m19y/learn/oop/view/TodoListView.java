package com.m19y.learn.oop.view;

import com.m19y.learn.oop.service.TodoListService;
import com.m19y.learn.oop.util.InputUtil;

public class TodoListView {
  private final TodoListService service;

  public TodoListView(TodoListService service) {
    this.service = service;
  }

  public void showTodoList(){
    while(true){
      service.showTodoList();
      System.out.println("\n====>> Menu <<====");
      System.out.println("1. Tambah");
      System.out.println("2. Hapus");
      System.out.println("X. Keluar");

      String userInput = InputUtil.input("Pilih");
      if(userInput.equals("1") || userInput.equalsIgnoreCase("Tambah")){
        addTodoList();
      }else if(userInput.equals("2") || userInput.equalsIgnoreCase("Hapus")){
        removeTodoList();
      } else if (userInput.equalsIgnoreCase("X") || userInput.equalsIgnoreCase("Keluar") ) {
        break;
      }else{
        System.err.println("Pilihan anda tidak dimengerti!");
      }
    }
  }

  public void addTodoList(){
    System.out.println("Menambah TODO");
    String todo = InputUtil.input("Todo (x Jika Batal)");

    if(!todo.equalsIgnoreCase("x")){
      service.addTodoList(todo);
    }
  }

  public void removeTodoList(){
    System.out.println("MENGHAPUS TODO");
    String number = InputUtil.input("Nomor yang di hapus (x Jika Batal)");

    if(!number.equalsIgnoreCase("x")) {
      try {
        service.removeTodoList(Integer.valueOf(number));
      } catch (NumberFormatException e) {
        System.err.println("Silahkan Pilih angka!");
      }
    }
  }
}
