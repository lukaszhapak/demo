package com.example.demo.test.integration.product.io.data.databaseConnections;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("postgres-flyway")
class PostgresFlywayTest {

  @Autowired
  ProductService productService;

  //@Test // requires postgres database with test_flyway schema, fly way is able to create schema, can be found in docker compose
  void shouldSaveProduct() {
	Product response = productService.save(TestData.getSampleProduct());
  }
}