package com.example.demo.commons;

import com.example.demo.modules.student.api.Student;
import java.util.List;

public abstract class TestUtils {

  public static Student getStudent() {
	Student student = new Student();
	student.setName("John");
	student.setAge(22);
	student.setGrades(List.of(2, 5, 4, 3, 3));
	return student;
  }
}
