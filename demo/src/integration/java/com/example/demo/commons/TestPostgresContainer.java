package com.example.demo.commons;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;
import java.util.Properties;

@Slf4j
public class TestPostgresContainer extends PostgreSQLContainer<TestPostgresContainer> {

  private static final String DEFAULT_IMAGE_VERSION = "postgres:13.2";
  private static final String CONFIG_IMAGE_PROPERTY_NAME = "postgres.test.docker.image.name";

  private static TestPostgresContainer container;

  TestPostgresContainer(String postgresDockerImageName) {
	super(postgresDockerImageName);
  }

  public static TestPostgresContainer getInstance() {
	String postgresDockerImageName = DEFAULT_IMAGE_VERSION;

	try {
	  Properties props = new Properties();
	  props.load(TestPostgresContainer.class.getResourceAsStream("/application.properties"));
	  postgresDockerImageName = Optional.ofNullable(props.getProperty(CONFIG_IMAGE_PROPERTY_NAME)).orElseThrow();
	} catch (Exception e) {
	}

	if (container == null) {
	  container = new TestPostgresContainer(postgresDockerImageName);
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
	//do nothing, JVM handles shut down
  }

}