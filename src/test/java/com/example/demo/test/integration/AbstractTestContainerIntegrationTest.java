package com.example.demo.test.integration;

import com.example.demo.commons.AbstractIntegrationTest;
import com.example.demo.commons.util.TestPostgresContainer;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ActiveProfiles("testcontainers")
public abstract class AbstractTestContainerIntegrationTest extends AbstractIntegrationTest {

  @Container
  public static TestPostgresContainer postgres = TestPostgresContainer.getContainer();

}
