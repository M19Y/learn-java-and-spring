package com.m19y.learn.oop.service;

import com.m19y.learn.oop.repository.TodoListRepository;
import com.m19y.learn.oop.repository.TodoListRepositoryImpl;
import com.m19y.learn.oop.view.TodoListView;


public class TodoListViewTest {


  static final TodoListRepository repository = new TodoListRepositoryImpl();
  static final TodoListService service = new TodoListServiceImpl(repository);
  static final TodoListView view = new TodoListView(service);

  public static void main(String[] args) {
    testRemoveTodoList();
  }


  public static void testShowTodoList() {

    service.addTodoList("Simple first");
    service.addTodoList("Simple second");
    service.addTodoList("Simple third");
    service.addTodoList("Simple forth");

    view.showTodoList();
  }

  public static void testAddTodoList() {
    view.addTodoList();
    service.showTodoList();
    view.addTodoList();
    service.showTodoList();

  }

  public static void testRemoveTodoList(){
    service.addTodoList("Simple first");
    service.addTodoList("Simple second");
    service.addTodoList("Simple third");
    service.addTodoList("Simple forth");

    service.showTodoList();

    view.removeTodoList();

    service.showTodoList();
  }
}