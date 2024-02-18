package com.example.demo.spring.tools.outbox;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractRestAssuredIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class OutboxTest extends AbstractRestAssuredIntegrationTest {

  @Autowired
  StudentService studentService;
  @Autowired
  OutboxJob outboxJob;
  @Autowired
  OutboxRepository outboxRepository;
  @Autowired
  StudentRepository studentRepository;

  private static Student getStudent() {
	Student student = new Student();
	student.setName("John");
	student.setAge(24);
	return student;
  }

  @Test
  @DisplayName("should send message")
  void shouldSendMessage() {
	// given
	studentService.save(getStudent());
	studentService.save(getStudent());
	studentService.save(getStudent());
	List<Outbox> messagesBeforeJob = outboxRepository.findAll();
	List<Student> students = studentRepository.findAll();

	// when
	outboxJob.sendMessages();

	// then
	List<Outbox> messagesAfterJob = outboxRepository.findAll();
	assertThat(students.size()).isEqualTo(3);
	assertThat(messagesBeforeJob.size()).isEqualTo(3);
	assertThat(messagesAfterJob.size()).isEqualTo(3);
	assertThat(messagesBeforeJob).noneMatch(Outbox::isSent);
	assertThat(messagesAfterJob).allMatch(Outbox::isSent);
  }
}
