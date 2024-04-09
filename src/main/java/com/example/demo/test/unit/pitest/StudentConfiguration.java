package com.example.demo.test.unit.pitest;

class StudentConfiguration {

  StudentFacade studentFacade(StudentRepository studentRepository, StudentEventPublisher studentEventPublisher) {
	StudentValidator studentValidator = new StudentValidator();
	return new StudentFacade(studentRepository, studentEventPublisher, studentValidator);
  }
}
