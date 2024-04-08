package com.example.demo.spring.message.kafka;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaEventPublisher {

  private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;

  @Scheduled(cron = "${kafka.publisher.cron}")
  public void publishEvent() {
	KafkaEvent kafkaEvent = new KafkaEvent(UUID.randomUUID().toString());
	log.debug("Publishing event sampleEvent={}", kafkaEvent);
	kafkaTemplate.send("test-topic", kafkaEvent);
  }
}
