//package com.example.demo.kafka;
//
//import java.time.LocalDateTime;
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//class KafkaMessageSender {
//
//  private final KafkaTemplate<String, String> kafkaTemplate;
//
//  @Scheduled(fixedDelay = 10000)
//  public void sendMessage() {
//	kafkaTemplate.send("test-topic", "key1", "message  " + LocalDateTime.now());
//  }
//}
