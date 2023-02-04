package com.example.clinic.commons;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestPostgresContainer extends PostgreSQLContainer {

  private static final TestPostgresContainer container = new TestPostgresContainer("postgres:13.2");

  TestPostgresContainer(String postgresDockerImageName) {
	super(postgresDockerImageName);
  }

  public static TestPostgresContainer getContainer() {
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