package com.example.demo.commons;

import com.example.demo.student.Student;

public abstract class TestUtils {

  public static Student getStudent() {
	Student studentEntity = new Student();
	studentEntity.setId(1L);
	studentEntity.setName("John");
	studentEntity.setAge(22);
	return studentEntity;
  }
}
