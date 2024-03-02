package com.example.demo.test.pitest.student;

class StudentValidator {

  void validate(Student student) {
	if (student.name.length() < 2) {
	  throw new IllegalArgumentException("name is to short");
	}
	if (student.age > 125) {
	  throw new IllegalArgumentException("age is to high");
	}
  }
}
