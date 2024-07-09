package com.example.demo.test.unit.testingApproach.behavior;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class StudentConfiguration {

  @Bean
  StudentFacade studentFacade(StudentRepository studentRepository, StudentEventPublisher studentEventPublisher) {

	// create objects here or use extracted methods

	StudentValidator studentValidator = new StudentValidator(studentRepository);
	StudentMapper studentMapper = new StudentMapper();

	return new StudentFacade(studentRepository, studentEventPublisher, studentValidator(studentRepository), studentMapper());
  }

  StudentValidator studentValidator(StudentRepository studentRepository) {
	return new StudentValidator(studentRepository);
  }

  StudentMapper studentMapper() {
	return new StudentMapper();
  }
}
