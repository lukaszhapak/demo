package com.example.demo.test.integration.product.databaseConnections;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import com.example.demo.test.integration.product.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("mysql")
class MysqlTest {

  @Autowired
  ProductRepository productRepository;

  //@Test // requires mysql database, can be found in docker compose
  void shouldSaveProduct() {
	Product response = productRepository.save(TestData.getSampleProduct());
  }
}