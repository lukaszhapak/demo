package com.example.demo.test.integration.testContainer.properties;

import com.example.demo.test.integration.Product;
import com.example.demo.test.integration.SampleProducts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestContainerFromProperties3Test extends AbstractTestContainerFromPropertiesTest implements SampleProducts  {

  @Test
  @DisplayName("Test name")
  void testName() {
	Product response = productRepository.save(sampleProduct1);
  }
}