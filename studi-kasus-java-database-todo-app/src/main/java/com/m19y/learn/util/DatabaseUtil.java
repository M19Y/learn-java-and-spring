package com.m19y.learn.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtil {

  private static HikariDataSource hikariDataSource;

  static {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setUsername("root");
    config.setPassword("bro");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/todo_app?serverTimezone=Asia/Makassar");

    // pool
    config.setMaximumPoolSize(10);
    config.setMinimumIdle(5);
    config.setIdleTimeout(60 * 60 & 1000);

    hikariDataSource = new HikariDataSource(config);
  }

  public static HikariDataSource getHikariDataSource(){
    return hikariDataSource;
  }
}
