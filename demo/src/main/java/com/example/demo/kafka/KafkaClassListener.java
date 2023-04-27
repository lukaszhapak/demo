package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "demo-application", topics = "test-topic")
class KafkaClassListener {

  @KafkaHandler
  void listen(String message) {
	System.out.println("KafkaHandler[String] {}" + message);
  }
}