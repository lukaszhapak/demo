package com.example.demo.test.refactoring.pitest;

class StudentValidator {

  void validate(Student student) {
	if (student.name.length() < 2) {
	  throw new IllegalArgumentException("name is too short");
	}
	if (student.age > 125) {
	  throw new IllegalArgumentException("age is too high");
	}
  }
}
