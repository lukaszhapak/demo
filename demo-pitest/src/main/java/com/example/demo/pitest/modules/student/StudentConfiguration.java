package com.example.demo.pitest.modules.student;

class StudentConfiguration {

  StudentFacade studentFacade(StudentRepository studentRepository, MessagePublisher messagePublisher) {
	StudentValidator studentValidator = new StudentValidator();
	return new StudentFacade(studentRepository, messagePublisher, studentValidator);
  }
}
