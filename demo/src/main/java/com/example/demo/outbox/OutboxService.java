package com.example.demo.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class OutboxService {

  private final OutboxRepository outboxRepository;
  private final ObjectMapper objectMapper;

  void registerMessage(Object message, String destination) {
	log.debug("Registering message={}, destination={}", message, destination);
	Outbox outbox = Outbox.builder()
		.eventClass(message.getClass())
		.body(parseMessage(message))
		.destination(destination)
		.sent(false)
		.build();
	outboxRepository.save(outbox);
  }

  List<Outbox> getUnsentMessages() {
	log.debug("Getting unsent messages");
	return outboxRepository.findBySent(false);
  }

  void markMessageAsSent(Outbox message) {
	log.debug("Marking message as sent message={}", message);
	outboxRepository.save(message);
  }

  private String parseMessage(Object message) {
	try {
	  return objectMapper.writeValueAsString(message);
	} catch (JsonProcessingException e) {
	  throw new RuntimeException(e);
	}
  }
}
