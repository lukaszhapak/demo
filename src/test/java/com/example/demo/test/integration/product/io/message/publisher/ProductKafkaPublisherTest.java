package com.example.demo.test.integration.product.io.message.publisher;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.test.context.EmbeddedKafka;

@Import(TestKafkaPublisherConfig.class)
@EmbeddedKafka(topics = "test-product-added-topic", partitions = 1)
@SpringBootTest(properties =
	{"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
		"spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}",
		"spring.kafka.consumer.auto-offset-reset=earliest"})
class ProductKafkaPublisherTest {

  @Autowired
  ProductService productService;

  @Autowired
  TestKafkaListener testKafkaListener;

  @Test
  @DisplayName("should send kafka event")
  void shouldSendKafkaEvent() {
	// given
	Product product = TestData.getSampleProduct();

	// when
	productService.saveAndPublishEvent(product);

	// then
	await()
		.atMost(Duration.ofMillis(3000))
		.with()
		.pollInterval(Duration.ofMillis(10))
		.until(() -> testKafkaListener.receivedRecords.size() == 1);
  }
}
