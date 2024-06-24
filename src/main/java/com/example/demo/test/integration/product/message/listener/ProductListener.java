package com.example.demo.test.integration.product.message.listener;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductListener {

  private final ProductService productService;

  @KafkaListener(id = "demo-application", topics = "test-product-topic")
  void listen(ConsumerRecord<String, KafkaEvent> kafkaEvent) {
	log.debug("Event received kafkaEvent={}", kafkaEvent);
	Product product = productService.getById(kafkaEvent.value().getProductId());
	product.setKafkaValue(kafkaEvent.value().getValue());
	productService.save(product);
  }
}
