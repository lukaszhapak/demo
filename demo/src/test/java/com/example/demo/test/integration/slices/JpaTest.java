package com.example.demo.test.integration.slices;

import com.example.demo.test.integration.Product;
import com.example.demo.test.integration.ProductRepository;
import com.example.demo.test.integration.SampleProducts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class JpaTest {

  @Autowired
  ProductRepository productRepository;

  @Test
  @DisplayName("Test name")
  void testName() {
	Product save = productRepository.save(SampleProducts.product);

	productRepository.findById(save.getId());
  }
}
