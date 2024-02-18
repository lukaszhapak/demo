package com.example.demo.spring.tools.kafka;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.example.demo.commons.AbstractRestAssuredIntegrationTest;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.shaded.org.awaitility.Awaitility;

@EmbeddedKafka(topics = "test-topic")
@ActiveProfiles("embedded-kafka")
class KafkaListenerTest extends AbstractRestAssuredIntegrationTest {

  @Autowired
  KafkaTemplate<String, KafkaEvent> kafkaTemplate;
  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should send message")
  void shouldSendMessage() {
	// given
	KafkaEvent kafkaEvent = new KafkaEvent("Jim", 24);
	long studentCountBeforeSendingMessage = studentRepository.count();

	// when
	kafkaTemplate.send("test-topic", kafkaEvent);

	// then
	Awaitility.await()
		.pollInterval(Duration.ofMillis(10))
		.timeout(Duration.ofSeconds(1))
		.untilAsserted(() ->
			assertThat(studentRepository.count())
				.describedAs("Student was persisted")
				.isEqualTo(studentCountBeforeSendingMessage + 1));
  }
}
