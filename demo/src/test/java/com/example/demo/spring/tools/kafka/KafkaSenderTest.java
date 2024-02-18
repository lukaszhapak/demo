package com.example.demo.spring.tools.kafka;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@EmbeddedKafka(topics = "test-topic")
@ActiveProfiles("embedded-kafka")
class KafkaSenderTest extends AbstractIntegrationTest {

  @Autowired
  KafkaMessageSender kafkaMessageSender;
  @Autowired
  KafkaTestConsumer testConsumer;

  @BeforeEach
  void resetListener() {
	testConsumer.resetLatch();
  }

  @Test
  @DisplayName("should send message")
  void shouldSendMessage() throws ExecutionException, InterruptedException {
	// given

	// when
	kafkaMessageSender.sendEvent();

	// then
	assertThat(testConsumer.receive(Duration.ofSeconds(2)).value().getName()).isEqualTo("John");
  }

  @TestConfiguration
  static class KafkaTestConfig {

	@Bean
	KafkaTestConsumer testConsumer() {
	  return new KafkaTestConsumer();
	}
  }

  @Slf4j
  static class KafkaTestConsumer {

	CompletableFuture<ConsumerRecord<String, KafkaEvent>> receivedRecord = new CompletableFuture<>();

	@KafkaListener(id = "test-demo-application", topics = "test-topic")
	void listen(ConsumerRecord<String, KafkaEvent> kafkaEvent) {
	  log.debug("Event received kafkaEvent={}", kafkaEvent);
	  receivedRecord.complete(kafkaEvent);
	}

	ConsumerRecord<String, KafkaEvent> receive(Duration timeout) throws ExecutionException, InterruptedException {
	  return receivedRecord.orTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS).get();
	}

	void resetLatch() {
	  receivedRecord = new CompletableFuture<>();
	}
  }
}
