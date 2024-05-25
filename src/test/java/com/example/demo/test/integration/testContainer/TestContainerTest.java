package com.example.demo.test.integration.testContainer;

import com.example.demo.commons.AbstractTestContainerIntegrationTest;
import com.example.demo.test.integration.data.Product;
import com.example.demo.test.integration.data.ProductRepository;
import com.example.demo.test.integration.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TestContainerTest extends AbstractTestContainerIntegrationTest {

  @Autowired
  ProductRepository productRepository;

  @Test
  void shouldSaveProduct() {
	Product response = productRepository.save(TestData.getSampleProduct());
  }
}