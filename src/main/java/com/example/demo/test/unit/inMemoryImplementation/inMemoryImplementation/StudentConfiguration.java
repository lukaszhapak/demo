package com.example.demo.test.unit.inMemoryImplementation.inMemoryImplementation;

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
