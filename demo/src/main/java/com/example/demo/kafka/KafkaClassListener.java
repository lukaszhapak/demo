package com.example.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(	value="kafka.enabled",	havingValue = "true")
class KafkaClassListener {

  @KafkaListener(id = "demo-application", topics = "test-topic")
  void listen(KafkaEvent kafkaEvent) {
	log.debug("Event received kafkaEvent={}", kafkaEvent);
  }
}