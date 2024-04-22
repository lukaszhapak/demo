package com.example.demo.test.unit.blackBox.blackBox;

class StudentConfiguration {

  StudentFacade studentFacade(StudentRepository studentRepository, StudentEventPublisher studentEventPublisher) {
	return new StudentFacade(studentRepository, studentEventPublisher, studentValidator(studentRepository), studentMapper());
  }

  StudentValidator studentValidator(StudentRepository studentRepository) {
	return new StudentValidator(studentRepository);
  }

  StudentMapper studentMapper() {
	return new StudentMapper();
  }
}
