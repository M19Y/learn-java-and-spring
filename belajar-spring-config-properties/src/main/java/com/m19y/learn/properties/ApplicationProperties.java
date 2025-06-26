package com.m19y.learn.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("application")
public class ApplicationProperties {

  private String owner;
  private String name;
  private Integer version;
  private Boolean productionMode;
  private DatabaseProperties database;
  private List<Role> defaultRoles;
  private Map<String, Role> roles;

  // converter
  private Duration defaultTimeout;

  // costume converter
  private Date expiredDate;

  @Getter
  @Setter
  public static class DatabaseProperties {

    private String database;
    private String username;
    private String password;
    private String url;

    // with collection
    private List<String> whitelistTables;
    private Map<String, Integer> maxTablesSize;
  }

  @Getter
  @Setter
  public static class Role{
    private String id;
    private String name;
  }
}
