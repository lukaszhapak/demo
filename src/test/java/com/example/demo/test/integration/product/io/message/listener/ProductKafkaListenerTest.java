package com.example.demo.test.integration.product.io.message.listener;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.message.listener.IncomingKafkaEvent;
import com.example.demo.test.integration.product.service.ProductService;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("kafka-embedded")
@EmbeddedKafka(topics = "test-product-topic", partitions = 1)
@SpringBootTest(properties = {"product.listener.enabled=true"})
class ProductKafkaListenerTest {

  @Autowired
  ProductService productService;

  @Autowired
  KafkaTemplate<String, IncomingKafkaEvent> kafkaTemplate;

  @Test
  @DisplayName("should handle kafka event")
  void shouldHandleKafkaEvent() {
	// given
	Long id = productService.save(TestData.getSampleProduct()).getId();
	IncomingKafkaEvent event = new IncomingKafkaEvent().setProductId(id).setValue("value from kafka");

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
