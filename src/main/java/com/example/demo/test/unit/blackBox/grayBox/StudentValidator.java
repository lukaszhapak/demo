package com.example.demo.test.unit.blackBox.grayBox;

class StudentValidator {

  void validate(Student student) {
	if (student.getName().length() < 2) {
	  throw new IllegalArgumentException("name is too short");
	}
	if (student.getAge() > 125) {
	  throw new IllegalArgumentException("age is too high");
	}
  }
}
