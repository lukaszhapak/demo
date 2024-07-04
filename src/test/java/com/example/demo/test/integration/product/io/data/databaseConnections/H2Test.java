package com.example.demo.test.integration.product.io.data.databaseConnections;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2")
class H2Test {

  @Autowired
  ProductService productService;

  @Test
  void shouldSaveProduct() {
	Product response = productService.save(TestData.getSampleProduct());
  }
}