package com.example.demo.test.integration.testContainer;

import com.example.demo.test.integration.AbstractTestContainerIntegrationTest;
import com.example.demo.test.integration.Product;
import com.example.demo.test.integration.ProductRepository;
import com.example.demo.test.integration.SampleProducts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("testcontainers-p6spy")
class TestContainerP6SpyTest extends AbstractTestContainerIntegrationTest implements SampleProducts {

  @Autowired
  ProductRepository productRepository;

  @Test
  @DisplayName("Test name")
  void testName() {
	Product response = productRepository.save(sampleProduct1);
  }
}