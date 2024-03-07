package com.example.demo.test.refactoring.pitest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class StudentFacade {

  private final StudentRepository studentRepository;
  private final MessagePublisher messagePublisher;
  private final StudentValidator studentValidator;

  Student save(Student student) {
	log.debug("saving student={}", student);
	studentValidator.validate(student);
	messagePublisher.publishStudentSavedEvent(student);
	studentRepository.save(student);
	return student;
  }
}
