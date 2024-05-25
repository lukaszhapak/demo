package com.example.demo.test.integration.testContainer;

import com.example.demo.test.integration.data.Product;
import com.example.demo.test.integration.data.ProductRepository;
import com.example.demo.test.integration.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// https://github.com/testcontainers/workshop/blob/main/step-4-your-first-testcontainers-integration.md
// https://java.testcontainers.org/modules/databases/jdbc/
@SpringBootTest(properties = {
	"spring.datasource.url=jdbc:tc:postgresql:14.0://demo"
})
class TestContainerFromPropertiesTest {

  @Autowired
  ProductRepository productRepository;

  @Test
  void shouldSaveProduct() {
	Product response = productRepository.save(TestData.getSampleProduct());
  }
}