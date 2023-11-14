package com.example.demo.spring.outbox;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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