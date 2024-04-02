package com.example.demo.test.refactoring.pitest;

class StudentConfiguration {

  StudentFacade studentFacade(StudentRepository studentRepository, StudentEventPublisher studentEventPublisher) {
	StudentValidator studentValidator = new StudentValidator();
	return new StudentFacade(studentRepository, studentEventPublisher, studentValidator);
  }
}
