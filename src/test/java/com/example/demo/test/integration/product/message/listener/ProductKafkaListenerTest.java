package com.example.demo.test.integration.product.message.listener;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.service.ProductService;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

@EmbeddedKafka(topics = "test-product-topic", partitions = 1)
@SpringBootTest(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
	"spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}"})
class ProductKafkaListenerTest {

  @Autowired
  ProductService productService;

  @Autowired
  KafkaTemplate<String, KafkaEvent> kafkaTemplate;

  @Test
  @DisplayName("should handle kafka event")
  void shouldHandleKafkaEvent() {
	// given
	Long id = productService.save(TestData.getSampleProduct()).getId();
	KafkaEvent event = new KafkaEvent().setProductId(id).setValue("value from kafka");

	// when
	kafkaTemplate.send("test-product-topic", event);

	// then
	await()
		.atMost(Duration.ofMillis(3000))
		.with()
		.pollInterval(Duration.ofMillis(10))
		.until(() -> "value from kafka".equals(productService.getById(id).getKafkaValue()));
  }
}
