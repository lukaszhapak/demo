package com.example.demo.spring.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KafkaClassListener {

  @KafkaListener(id = "demo-application", topics = "test-topic")
  void listen(ConsumerRecord<String, KafkaEvent> kafkaEvent) {
	log.debug("Event received kafkaEvent={}", kafkaEvent);
  }
}