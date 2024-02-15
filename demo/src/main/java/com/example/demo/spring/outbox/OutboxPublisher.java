package com.example.demo.spring.outbox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class OutboxPublisher {

  void publish(Outbox message) {
	log.debug("Publishing message={}", message);
  }
}
