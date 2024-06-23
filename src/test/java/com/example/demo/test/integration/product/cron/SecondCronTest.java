package com.example.demo.test.integration.product.cron;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"product.cron=* * * ? * *"})
class SecondCronTest {

  @Autowired
  ProductService productService;

  @Test
  @DisplayName("should save product and run job")
  void shouldSaveProductAndRunJob() {
	// given
	Long id = productService.save(new Product().setName("asd")).getId();

	// when
	// job is running in the background

	// then
	await()
		.atMost(Duration.ofMillis(1500))
		.with()
		.pollInterval(Duration.ofMillis(10))
		.until(() -> productService.getById(id).isCronValue());
  }
}
