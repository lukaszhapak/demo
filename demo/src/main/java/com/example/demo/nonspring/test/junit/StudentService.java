package com.example.demo.nonspring.test.junit;

class StudentService {

  boolean validateStudent(Student student) {
	return student.getName().length() > 2
		&& student.getName().length() < 64;
  }
}