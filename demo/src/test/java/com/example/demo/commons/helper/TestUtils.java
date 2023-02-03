package com.example.demo.commons.helper;

import com.example.demo.modules.student.Student;
import java.util.List;

public interface TestUtils {

  static Student getStudent() {
	Student student = new Student();
	student.setName("John");
	student.setAge(22);
	student.setGrades(List.of(2, 5, 4, 3, 3));
	return student;
  }
}
