package com.example.demo.spring.modules.message.kafka;

import com.example.demo.spring.base.entity.Student;
import com.example.demo.spring.base.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class KafkaService {

  private final StudentRepository studentRepository;
  private final KafkaEventPublisher kafkaEventPublisher;

  public Student saveAndPublishEvent(Student student) {
	log.debug("saving student={}", student);
	kafkaEventPublisher.publishStudentRegisteredEvent(student);
	return studentRepository.save(student);
  }
}

