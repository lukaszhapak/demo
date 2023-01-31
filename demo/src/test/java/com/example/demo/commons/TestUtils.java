package com.example.demo.commons;

import com.example.demo.student.Student;

public abstract class TestUtils {

  public static Student getStudent() {
	Student student = new Student();
	student.setId(1L);
	student.setName("John");
	student.setAge(22);
	return student;
  }
}
