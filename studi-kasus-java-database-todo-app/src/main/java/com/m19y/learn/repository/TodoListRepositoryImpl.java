package com.m19y.learn.repository;

import com.m19y.learn.entity.Todo;
import com.m19y.learn.util.QueryUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TodoListRepositoryImpl implements TodoListRepository {

  private final DataSource dataSource;

  public TodoListRepositoryImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Todo[] getAll() {
    String findAllTodosSQL = "SELECT id, todo FROM todolist";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(findAllTodosSQL);
        ResultSet resultSet = statement.executeQuery()){

      List<Todo> list = new ArrayList<>();
      while (resultSet.next()){
        Todo todo = new Todo();
        todo.setTodo(resultSet.getString("todo"));
        todo.setId(resultSet.getInt("id"));
        list.add(todo);
      }
      return list.toArray(new Todo[]{});
    }catch (SQLException exception){
      throw new RuntimeException(exception);
    }
  }

  @Override
  public void add(Todo todo) {

    String addTodoSQL = "INSERT INTO todolist(todo) VALUES (?)";

    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(addTodoSQL)){

      statement.setString(1, todo.getTodo());
      statement.executeUpdate();

    }catch (SQLException exception){
      throw new RuntimeException(exception);
    }

  }

  @Override
  public boolean remove(Integer number) {
    if(!QueryUtil.findByIdIfExists(number)){
      System.err.printf("todo with id: %d was not found\n", number);
      return false;
    }

    String findSQL = "DELETE FROM todolist WHERE id = ?";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(findSQL)){
      statement.setInt(1, number);
      statement.executeUpdate();
      return true;
    }catch (SQLException exception){
      throw new RuntimeException(exception);
    }

  }
}
