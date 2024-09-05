package com.example.demo.spring.modules.message.kafka;

import com.example.demo.spring.base.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaEventPublisher {

  private final KafkaTemplate<String, KafkaStudentRegisteredEvent> kafkaTemplate;

  public void publishStudentRegisteredEvent(Student student) {
	KafkaStudentRegisteredEvent kafkaStudentRegisteredEvent = new KafkaStudentRegisteredEvent().setFirstName(student.getFirstName());
	log.debug("Publishing event studentRegisteredKafkaEvent={}", kafkaStudentRegisteredEvent);
	kafkaTemplate.send("demo-student-registered-topic", kafkaStudentRegisteredEvent);
  }
}
