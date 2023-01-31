package com.example.demo.student;

import org.springframework.stereotype.Component;

@Component
class StudentValidator {

  void validate(Student student) {
	if (student.getAge() > 100) {
	  throw new RuntimeException("Student too old");
	}
	if (student.getAge() < 20) {
	  throw new RuntimeException("Student too young");
	}
	if (student.getName().length() > 64) {
	  throw new RuntimeException("Name too long");
	}
	if (student.getName().length() < 2) {
	  throw new RuntimeException("Name too short");
	}
  }
}
