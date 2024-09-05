package com.example.demo.spring.modules.tools.outbox;

import com.example.demo.spring.base.entity.Student;
import com.example.demo.spring.base.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class OutboxStudentService {

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
