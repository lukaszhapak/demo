package com.example.demo.test.integration.testContainer.properties;

import com.example.demo.test.integration.ProductRepository;
import com.example.demo.test.integration.SampleProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
	"spring.datasource.url=jdbc:tc:postgresql:14.0://demo"
})
abstract class AbstractTestContainerFromPropertiesTest implements SampleProducts {

  @Autowired
  ProductRepository productRepository;
}