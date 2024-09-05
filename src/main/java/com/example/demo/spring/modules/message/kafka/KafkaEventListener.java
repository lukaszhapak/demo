package com.example.demo.spring.modules.message.kafka;

import com.example.demo.spring.base.entity.Student;
import com.example.demo.spring.base.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaEventListener {

  private final StudentService studentService;

  @KafkaListener(id = "demo-application", topics = "demo-student-updated-topic")
  void handleKafkaEvent(ConsumerRecord<String, KafkaStudentUpdatedEvent> studentUpdatedEvent) {
	log.debug("Event received studentUpdatedEvent={}", studentUpdatedEvent);
	Student student = studentService.findById(studentUpdatedEvent.value().getId());
	student.setFirstName(studentUpdatedEvent.value().getFirstName());
	studentService.save(student);
  }
}