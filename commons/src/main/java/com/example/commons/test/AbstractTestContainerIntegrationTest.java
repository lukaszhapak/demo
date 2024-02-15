package com.example.commons.test;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractTestContainerIntegrationTest extends AbstractIntegrationTest {

  @Container
  public static TestPostgresContainer postgres = TestPostgresContainer.getContainer();

}
