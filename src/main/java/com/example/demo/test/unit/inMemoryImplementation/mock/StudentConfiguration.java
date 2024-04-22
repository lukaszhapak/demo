package com.example.demo.test.unit.inMemoryImplementation.mock;

class StudentConfiguration {

  StudentFacade studentFacade(StudentRepository studentRepository, StudentEventPublisher studentEventPublisher) {
	return new StudentFacade(studentRepository, studentEventPublisher, studentValidator(), studentMapper());
  }

  StudentValidator studentValidator() {
	return new StudentValidator();
  }

  StudentMapper studentMapper() {
	return new StudentMapper();
  }
}
