package com.example.demo.spring.message.kafka;


import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.example.demo.commons.AbstractIntegrationTest;
import java.util.LinkedList;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@EmbeddedKafka(topics = "test-topic")
@ActiveProfiles("kafka-embedded")
class KafkaSenderTest extends AbstractIntegrationTest {

  @Autowired
  KafkaMessageSender kafkaMessageSender;
  @Autowired
  KafkaTestConsumer testConsumer;

  @Test
  @DisplayName("should send message")
  void shouldSendMessage() {
	// given

	// when
	kafkaMessageSender.sendEvent();

	// then
	await().atMost(ofSeconds(2)).pollInterval(ofMillis(20)).untilAsserted(() ->
		assertThat(testConsumer.receivedRecords.size()).describedAs("Message was received").isEqualTo(1));
  }

  @TestConfiguration
  static class KafkaTestConfig {

	@Bean
	KafkaTestConsumer testConsumer() {
	  return new KafkaTestConsumer();
	}
  }

  static class KafkaTestConsumer {

	List<KafkaEvent> receivedRecords = new LinkedList<>();

	@KafkaListener(id = "test-demo-application", topics = "test-topic")
	void listen(ConsumerRecord<String, KafkaEvent> kafkaEvent) {
	  receivedRecords.add(kafkaEvent.value());
	}
  }
}
