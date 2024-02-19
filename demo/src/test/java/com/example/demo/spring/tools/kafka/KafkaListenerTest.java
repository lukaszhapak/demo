package com.example.demo.spring.tools.kafka;


import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@EmbeddedKafka(topics = "test-topic")
@ActiveProfiles("embedded-kafka")
class KafkaListenerTest extends AbstractIntegrationTest {

  @Autowired
  KafkaTemplate<String, KafkaEvent> kafkaTemplate;
  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should receive message")
  void shouldReceiveMessage() {
	// given
	KafkaEvent kafkaEvent = new KafkaEvent("Jim", 24);

	// when
	kafkaTemplate.send("test-topic", kafkaEvent);

	// then
	await().atMost(ofSeconds(2)).pollInterval(ofMillis(20)).untilAsserted(() ->
		assertThat(studentRepository.count()).describedAs("Student was persisted").isEqualTo(1));
  }
}
