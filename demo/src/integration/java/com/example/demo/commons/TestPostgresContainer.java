package com.example.demo.commons;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestPostgresContainer extends PostgreSQLContainer {

  private static TestPostgresContainer container;

  TestPostgresContainer(String postgresDockerImageName) {
	super(postgresDockerImageName);
  }

  public static TestPostgresContainer getInstance() {
	if (container == null) {
	  container = new TestPostgresContainer("postgres:13.2");
	}
	return container;
  }

  @Override
  public void start() {
	super.start();
	System.setProperty("DB_URL", container.getJdbcUrl());
	System.setProperty("DB_USERNAME", container.getUsername());
	System.setProperty("DB_PASSWORD", container.getPassword());
  }

  @Override
  public void stop() {
  }
}