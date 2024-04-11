package com.example.demo.test.integration.databaseConnections;

import com.example.demo.test.integration.Product;
import com.example.demo.test.integration.ProductRepository;
import com.example.demo.test.integration.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("postgres-p6spy")
class PostgresP6SpyTest {

  @Autowired
  ProductRepository productRepository;

  //@Test // requires postgres database with test schema, can be found in docker compose
  void shouldSaveProduct() {
	Product response = productRepository.save(TestData.getSampleProduct());
  }
}