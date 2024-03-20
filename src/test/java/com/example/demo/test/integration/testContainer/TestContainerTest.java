package com.example.demo.test.integration.testContainer;

import com.example.demo.test.integration.AbstractTestContainerIntegrationTest;
import com.example.demo.test.integration.Product;
import com.example.demo.test.integration.ProductRepository;
import com.example.demo.test.integration.SampleProducts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TestContainerTest extends AbstractTestContainerIntegrationTest implements SampleProducts {

  @Autowired
  ProductRepository productRepository;

  @Test
  @DisplayName("Test name")
  void testName() {
	Product response = productRepository.save(sampleProduct1);
  }
}