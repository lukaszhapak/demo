package com.example.demo.test.unit.testingApproach.behavior;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class StudentConfigurationWithFields {

  private final StudentRepository studentRepository;
  private final StudentEventPublisher studentEventPublisher;

  StudentFacade studentFacade() {
	return new StudentFacade(studentRepository, studentEventPublisher, studentValidator(), studentMapper());
  }

  StudentValidator studentValidator() {
	return new StudentValidator(studentRepository);
  }

  StudentMapper studentMapper() {
	return new StudentMapper();
  }
}
