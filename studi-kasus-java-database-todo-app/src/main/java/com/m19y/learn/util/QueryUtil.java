package com.m19y.learn.util;

import com.m19y.learn.entity.Todo;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryUtil {

  private static final HikariDataSource dataSource = DatabaseUtil.getHikariDataSource();

  public static boolean findByNameIfExists(String todo){
    String findSQL = "SELECT todo FROM todolist WHERE todo = ? LIMIT 1";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(findSQL)){
      statement.setString(1, todo);
      return statement.execute();
    }catch (SQLException exception){
      throw new RuntimeException(exception);
    }
  }


  public static Integer findByNameAndGetId(String todo){
    String findSQL = "SELECT id, todo FROM todolist WHERE todo = ? LIMIT 1";
    int id = 0;
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(findSQL)){
      statement.setString(1, todo);

      try(ResultSet resultSet = statement.executeQuery()){
        if(resultSet.next()){
          id = resultSet.getInt("id");
        }
      }
    }catch (SQLException exception){
      throw new RuntimeException(exception);
    }
    return id;
  }

  public static boolean findByIdIfExists(Integer todo){
    String findSQL = "SELECT id FROM todolist WHERE id = ?";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(findSQL)){
      statement.setInt(1, todo);
      try(ResultSet resultSet = statement.executeQuery()){
        return resultSet.next();
      }
    }catch (SQLException exception){
      throw new RuntimeException(exception);
    }
  }


  public static void deleteAllTodo(){

    String deleteSQL = "DELETE FROM todolist";

    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteSQL)){
      int totalDeletedTodos = statement.executeUpdate();
      System.out.println("Row deleted: " + totalDeletedTodos);
    }catch (SQLException exception){
      throw new RuntimeException(exception);
    }
  }
}
