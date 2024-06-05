package com.example.demo.test.integration.product.testContainer;

import com.example.demo.commons.AbstractTestContainerIntegrationTest;
import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import com.example.demo.test.integration.product.TestData;
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