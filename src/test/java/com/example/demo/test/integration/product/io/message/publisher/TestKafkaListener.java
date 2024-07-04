package com.example.demo.test.integration.product.io.message.publisher;

import com.example.demo.test.integration.product.message.publisher.ProductAddedEvent;
import java.util.LinkedList;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

class TestKafkaListener {

  List<ProductAddedEvent> receivedRecords = new LinkedList<>();

  @KafkaListener(id = "test-demo-application", topics = "test-product-added-topic")
  void listen(ConsumerRecord<String, ProductAddedEvent> kafkaEvent) {
	receivedRecords.add(kafkaEvent.value());
  }
}