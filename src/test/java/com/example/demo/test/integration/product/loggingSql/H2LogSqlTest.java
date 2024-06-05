package com.example.demo.test.integration.product.loggingSql;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import com.example.demo.test.integration.product.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2-log-sql")
class H2LogSqlTest {

  @Autowired
  ProductRepository productRepository;

  @Test
  void shouldSaveProduct() {
	Product response = productRepository.save(TestData.getSampleProduct());
  }
}