package com.example.demo.spring.outbox;

import com.example.demo.commons.AbstractIntegrationTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class OutboxJobTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;
  @Autowired
  OutboxJob outboxJob;
  @Autowired
  OutboxRepository outboxRepository;
  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("should send message")
  void shouldSendMessage() {
	// given
	studentService.save(getStudent());
	studentService.save(getStudent());
	studentService.save(getStudent());

	// when
	List<Outbox> messages = outboxRepository.findAll();
	List<Student> students = studentRepository.findAll();
	outboxJob.sendMessages();

	// then
	List<Outbox> messagesAfterJob = outboxRepository.findAll();
	List<Student> studentsAfterJob = studentRepository.findAll();
  }

  private static Student getStudent() {
	Student student = new Student();
	student.setName("John");
	student.setAge(24);
	return student;
  }
}
