package com.example.demo.commons;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DirtiesContext
public abstract class TestContainer {

  @Container
  private static final PostgreSQLContainer<?> postgreSQLContainer =
	  new PostgreSQLContainer<>("postgres:13.2");

  @DynamicPropertySource
  private static void containerConfig(DynamicPropertyRegistry registry) {
	registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
	registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
	registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }
}
