package com.example.demo.commons;

import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ActiveProfiles("testcontainers")
public abstract class AbstractTestContainerIntegrationTest extends AbstractIntegrationTest {

  @Container
  public static TestPostgresContainer postgres = TestPostgresContainer.getContainer();

}
