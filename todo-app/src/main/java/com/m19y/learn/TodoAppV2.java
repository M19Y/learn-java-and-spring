package com.m19y.learn;

import com.m19y.learn.oop.repository.TodoListRepository;
import com.m19y.learn.oop.repository.TodoListRepositoryImpl;
import com.m19y.learn.oop.service.TodoListService;
import com.m19y.learn.oop.service.TodoListServiceImpl;
import com.m19y.learn.oop.view.TodoListView;

public class TodoAppV2 {

  public static void main(String[] args) {
    final TodoListRepository repository = new TodoListRepositoryImpl();
    final TodoListService service = new TodoListServiceImpl(repository);
    final TodoListView view = new TodoListView(service);

    view.showTodoList();
  }
}
