package com.example.demo.test.integration.testData;

import com.example.demo.commons.AbstractIntegrationTest;
import com.example.demo.test.integration.SampleProducts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureTestEntityManager
class TestEntityManagerTest extends AbstractIntegrationTest implements SampleProducts {

  @Autowired
  TestEntityManager testEntityManager;

  @Test
  @DisplayName("Test name")
  void testName() {
	testEntityManager.persistFlushFind(product);
  }
}
