package com.example.demo.pitest.modules.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentFacade {

  private final StudentRepository studentRepository;
  private final MessagePublisher messagePublisher;
  private final StudentValidator studentValidator;

  public Student save(Student student) {
	log.debug("saving student={}", student);
	studentValidator.validate(student);
	messagePublisher.publishStudentSavedEvent(student);
	studentRepository.save(student);
	return student;
  }
}
