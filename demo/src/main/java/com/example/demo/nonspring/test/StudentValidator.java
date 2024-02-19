package com.example.demo.nonspring.test;

class StudentValidator {

  boolean validateStudent(Student student) {
	return student.getName().length() > 2
		&& student.getName().length() < 64;
  }
}
