package com.example.demo.test.integration.product.io.message.publisher;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.message.publisher.ProductAddedEvent;
import com.example.demo.test.integration.product.io.message.publisher.ProductKafkaPublisherTest.KafkaTestConfig;
import com.example.demo.test.integration.product.service.ProductService;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;

@Import(KafkaTestConfig.class)
@EmbeddedKafka(topics = "test-product-added-topic", partitions = 1)
@SpringBootTest(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
	"spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}",
	"spring.kafka.consumer.auto-offset-reset=earliest"})
class ProductKafkaPublisherTest {

  @Autowired
  ProductService productService;

  @Autowired
  KafkaTestListener testListener;

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
		.until(() -> testListener.receivedRecords.size() == 1);
  }

  @TestConfiguration
  static class KafkaTestConfig {

	@Bean
	static KafkaTestListener testListener() {
	  return new KafkaTestListener();
	}
  }

  static class KafkaTestListener {

	List<ProductAddedEvent> receivedRecords = new LinkedList<>();

	@KafkaListener(id = "test-demo-application", topics = "test-product-added-topic")
	void listen(ConsumerRecord<String, ProductAddedEvent> kafkaEvent) {
	  receivedRecords.add(kafkaEvent.value());
	}
  }
}
