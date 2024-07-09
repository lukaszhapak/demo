package com.example.demo.test.integration.product.message.listener;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty("product.listener.enabled")
public class ProductEventListener {

  private final ProductService productService;

  @KafkaListener(id = "demo-application", topics = "test-product-topic")
  void listen(ConsumerRecord<String, IncomingKafkaEvent> kafkaEvent) {
	Product product = productService.getById(kafkaEvent.value().getProductId());
	product.setKafkaValue(kafkaEvent.value().getValue());
	productService.save(product);
  }
}
