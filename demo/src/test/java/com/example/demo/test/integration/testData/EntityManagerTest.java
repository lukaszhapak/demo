package com.example.demo.test.integration.testData;

import com.example.demo.commons.AbstractIntegrationTest;
import com.example.demo.test.integration.SampleProducts;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class EntityManagerTest extends AbstractIntegrationTest implements SampleProducts {

  @Autowired
  EntityManager entityManager;

  @Test
  @DisplayName("Test name")
  void testName() {
	entityManager.persist(product);
  }
}
