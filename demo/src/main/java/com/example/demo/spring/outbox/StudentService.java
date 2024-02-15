package com.example.demo.spring.outbox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;
  private final OutboxService outboxService;

  @Transactional // require public
  public Student save(Student student) {
	log.debug("save student={}", student);
	Student savedStudent = studentRepository.save(student);
	outboxService.registerMessage(savedStudent, "student-registered-topic");
	return savedStudent;
  }
}
