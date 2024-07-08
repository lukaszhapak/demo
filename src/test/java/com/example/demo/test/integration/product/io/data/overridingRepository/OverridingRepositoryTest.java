package com.example.demo.test.integration.product.io.data.overridingRepository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.data.Product;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OverridingRepositoryTest {

  @Autowired
  TestProductRepository productRepository;

  @Test
  @DisplayName("should get product by integer value")
  void shouldGetProductByIntegerValue() {
	// given
	productRepository.save(TestData.getSampleProduct().setIntegerValue(42));

	// then
	assertThat(productRepository.findByIntegerValue(42)).isNotEmpty();
	assertThat(productRepository.count()).isGreaterThanOrEqualTo(1);
	List<Product> all = productRepository.findAll();
  }
}
