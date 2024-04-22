package com.example.demo.test.unit.testingApproach.behavior;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class StudentValidator {
  private final StudentRepository studentRepository;

  void validate(Student student) {
	if (student.getName().length() < 2) {
	  throw new IllegalArgumentException("name is too short");
	}
	if (student.getAge() > 125) {
	  throw new IllegalArgumentException("age is too high");
	}
  }
}
