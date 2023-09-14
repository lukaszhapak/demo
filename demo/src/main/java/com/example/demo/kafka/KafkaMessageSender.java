package com.example.demo.kafka;

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

  private final KafkaTemplate<String, SampleEvent> kafkaTemplate;

  @Scheduled(fixedDelay = 10000)
  public void sendEvent() {
	SampleEvent sampleEvent = new SampleEvent("John", 24, LocalDateTime.now());
	log.debug("Sending event sampleEvent={}", sampleEvent);
	kafkaTemplate.send("test-topic", "key1", sampleEvent);
  }
}
