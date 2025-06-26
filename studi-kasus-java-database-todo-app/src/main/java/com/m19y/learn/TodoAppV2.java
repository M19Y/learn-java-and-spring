package com.m19y.learn;

import com.m19y.learn.repository.TodoListRepository;
import com.m19y.learn.repository.TodoListRepositoryImpl;
import com.m19y.learn.service.TodoListService;
import com.m19y.learn.service.TodoListServiceImpl;
import com.m19y.learn.util.DatabaseUtil;
import com.m19y.learn.view.TodoListView;

import javax.sql.DataSource;

public class TodoAppV2 {

  public static void main(String[] args) {
    final DataSource dataSource = DatabaseUtil.getHikariDataSource();
    final TodoListRepository repository = new TodoListRepositoryImpl(dataSource);
    final TodoListService service = new TodoListServiceImpl(repository);
    final TodoListView view = new TodoListView(service);

    view.showTodoList();
  }
}
