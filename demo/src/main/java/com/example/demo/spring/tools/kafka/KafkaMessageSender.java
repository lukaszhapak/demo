package com.example.demo.spring.tools.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaMessageSender {

  private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;

  @Scheduled(cron = "${kafka.sender.cron}")
  public void sendEvent() {
	KafkaEvent kafkaEvent = new KafkaEvent("John", 24);
	log.debug("Sending event sampleEvent={}", kafkaEvent);
	kafkaTemplate.send("test-topic", kafkaEvent);
  }
}
