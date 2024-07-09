package com.example.demo.test.integration.product.io.cron;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.cron.ProductJob;
import com.example.demo.test.integration.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"product.cron=-"})
class ManuallyExecutingJobTest {

  @Autowired
  ProductJob productJob;

  @Autowired
  ProductService productService;

  @Test
  @DisplayName("should save product and run job")
  void shouldSaveProductAndRunJob() {
	// given
	Long id = productService.save(TestData.getSampleProduct()).getId();

	// when
	productJob.setCronValue();

	// then
	assertThat(productService.getById(id).isCronValue()).isTrue();
  }
}
