package com.example.demo.outbox;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OutboxJobTest {

  @Autowired
  OutboxService outboxService;
  @Autowired
  OutboxJob outboxJob;
  @Autowired
  OutboxRepository outboxRepository;


  @Test
  @DisplayName("should send message")
  void shouldSendMessage() {
	// given
	outboxService.registerMessage(getStudent(), "test-destination");
	outboxService.registerMessage(getStudent(), "test-destination");
	outboxService.registerMessage(getStudent(), "test-destination");
	outboxService.registerMessage(getStudent(), "test-destination");

	// when
	outboxJob.sendMessages();

	// then
	List<Outbox> messages = outboxRepository.findAll();
  }

  @NotNull
  private static Student getStudent() {
	Student student = new Student();
	student.setName("John");
	return student;
  }
}
