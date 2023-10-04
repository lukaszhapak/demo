package com.example.demo.spring.kafka;

import java.time.LocalDateTime;
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

  @Scheduled(fixedDelay = 10000)
  public void sendEvent() {
	KafkaEvent kafkaEvent = new KafkaEvent("John", 24, LocalDateTime.now());
	log.debug("Sending event sampleEvent={}", kafkaEvent);
	kafkaTemplate.send("test-topic", kafkaEvent);
  }
}
