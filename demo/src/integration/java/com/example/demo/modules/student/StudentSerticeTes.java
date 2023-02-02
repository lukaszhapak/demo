package com.example.demo.modules.student;

import com.example.demo.commons.AbstractIntegrationTest;
import com.example.demo.modules.student.api.Student;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StudentSerticeTes extends AbstractIntegrationTest {

  @Autowired StudentService studentService;

  @Test
  @DisplayName("Test name")
  void testName() {
	// given
	StudentSearchCriteria studentSearchCriteria = StudentSearchCriteria.builder()
		.minAge(23)
		.maxAge(41)
		.name("John")
		.build();
	List<Student> all = studentService.findAll(studentSearchCriteria);
	// when

	// then
  }

  @Test
  @DisplayName("grades")
  void grades() {
	// given
	StudentSearchCriteria studentSearchCriteria = StudentSearchCriteria.builder()
		.minAge(23)
		.maxAge(41)
		.name("John")
		.build();
	Student grades = studentService.getGrades(studentSearchCriteria);
	// when

	// then
  }
}
