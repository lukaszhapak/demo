package com.example.demo.nonspring.test;

class TestData {

  static Student getStudent() {
	return Student.builder()
		.id(14L)
		.name("John")
		.age(22)
		.build();
  }

  static StudentDTO getStudentDTO() {
	return StudentDTO.builder()
		.name("John")
		.age(22)
		.build();
  }

}
