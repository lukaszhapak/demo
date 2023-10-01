package com.example.demo.spring.outbox;

import com.example.demo.spring.outbox.Outbox;
import com.example.demo.spring.outbox.OutboxJob;
import com.example.demo.spring.outbox.OutboxRepository;
import com.example.demo.spring.outbox.OutboxService;
import com.example.demo.spring.outbox.Student;
import java.util.List;
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

  private static Student getStudent() {
	Student student = new Student();
	student.setName("John");
	return student;
  }
}
