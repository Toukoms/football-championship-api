package com.football_championship_api.demo.config;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

@Configuration
public class CustomDataSource {
  private final String DB_NAME = System.getenv("DB_NAME");
  private final String DB_HOST = Optional.ofNullable(System.getenv("DB_HOST")).orElse("localhost");
  private final String DB_PORT = Optional.ofNullable(System.getenv("DB_PORT")).orElse("5432");
  private final String DB_USERNAME = System.getenv("DB_USERNAME");
  private final String DB_PASSWORD = System.getenv("DB_PASSWORD");
  private String jdbcUrl;

  public CustomDataSource() {
    this.jdbcUrl = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
  }

  public Connection getConnection() {
    try {
      if (DB_NAME == null || DB_USERNAME == null || DB_PASSWORD == null) {
        throw new IllegalArgumentException("Missing database connection credentials");
      }
      return DriverManager.getConnection(this.jdbcUrl, DB_USERNAME, DB_PASSWORD);
    } catch (Exception e) {
      throw new RuntimeException("Error connection to database: ", e);
    }
  };
}
