package com.example.demo.test.integration.product.message.publisher;

import com.example.demo.test.integration.product.data.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventPublisher {

  private final KafkaTemplate<String, ProductAddedEvent> kafkaTemplate;

  public void publishProductAddedEvent(Product product) {
	ProductAddedEvent productAddedEvent = new ProductAddedEvent().setName(product.getName());
	kafkaTemplate.send("test-product-added-topic", productAddedEvent);
  }
}
