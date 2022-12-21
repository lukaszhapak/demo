package com.example.demo.student;

public interface StudentControllerTest {

  default Student getStudent() {
	Student student = new Student();
	student.setId(100L);
	student.setName("John");
	student.setAge(22);
	return student;
  }
}
