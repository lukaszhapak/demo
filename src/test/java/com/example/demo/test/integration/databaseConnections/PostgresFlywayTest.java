package com.example.demo.test.integration.databaseConnections;

import com.example.demo.test.integration.Product;
import com.example.demo.test.integration.ProductRepository;
import com.example.demo.test.integration.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("postgres-flyway")
class PostgresFlywayTest {

  @Autowired
  ProductRepository productRepository;

  //@Test // requires postgres database with test_flyway schema, fly way is able to create schema, can be found in docker compose
  void shouldSaveProduct() {
	Product response = productRepository.save(TestData.getSampleProduct());
  }
}