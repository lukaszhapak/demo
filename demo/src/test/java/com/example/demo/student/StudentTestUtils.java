package com.example.demo.student;

abstract class StudentTestUtils {

  static Student getStudent() {
	Student student = new Student();
	student.setId(1L);
	student.setName("John");
	student.setAge(22);
	return student;
  }
}
