package com.example.demo.test.refactoring.pitest;

class StudentConfiguration {

  StudentFacade studentFacade(StudentRepository studentRepository, MessagePublisher messagePublisher) {
	StudentValidator studentValidator = new StudentValidator();
	return new StudentFacade(studentRepository, messagePublisher, studentValidator);
  }
}
