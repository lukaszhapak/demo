package com.example.demo.test.integration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductListener {

  private final ProductRepository productRepository;

  @KafkaListener(id = "demo-application", topics = "test-topic")
  void listen(ConsumerRecord<String, KafkaEvent> kafkaEvent) {
	log.debug("Event received kafkaEvent={}", kafkaEvent);
	productRepository.save(new Product().setName(kafkaEvent.value().getBody()));
  }
}
