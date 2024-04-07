package com.example.demo.test.integration.testContainer.properties;

import com.example.demo.test.integration.ProductRepository;
import com.example.demo.test.integration.SampleProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// https://github.com/testcontainers/workshop/blob/main/step-4-your-first-testcontainers-integration.md
// https://java.testcontainers.org/modules/databases/jdbc/
@SpringBootTest(properties = {
	"spring.datasource.url=jdbc:tc:postgresql:14.0://demo"
})
abstract class AbstractTestContainerFromPropertiesTest implements SampleProducts {

  @Autowired
  ProductRepository productRepository;
}