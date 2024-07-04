package com.example.demo.test.integration.product.testContainer;

import com.example.demo.commons.AbstractTestContainerIntegrationTest;
import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("testcontainers-p6spy")
class TestContainerP6SpyTest extends AbstractTestContainerIntegrationTest {

  @Autowired
  ProductService productService;

  @Test
  void shouldSaveProduct() {
	Product response = productService.save(TestData.getSampleProduct());
  }
}