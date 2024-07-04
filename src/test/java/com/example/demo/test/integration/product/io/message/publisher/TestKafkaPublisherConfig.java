package com.example.demo.test.integration.product.io.message.publisher;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class TestKafkaPublisherConfig {

  @Bean
  static TestKafkaListener testKafkaListener() {
	return new TestKafkaListener();
  }
}
