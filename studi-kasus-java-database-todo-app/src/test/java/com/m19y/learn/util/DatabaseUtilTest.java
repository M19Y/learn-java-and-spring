package com.m19y.learn.util;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.SQLException;

class DatabaseUtilTest {

  @Test
  void testConnection() throws SQLException {

    HikariDataSource dataSource = DatabaseUtil.getHikariDataSource();

    Connection connection = dataSource.getConnection();

    connection.close();
    dataSource.close();
  }
}