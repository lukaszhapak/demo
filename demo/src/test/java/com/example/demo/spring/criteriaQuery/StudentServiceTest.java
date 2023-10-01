package com.example.demo.spring.criteriaQuery;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class StudentServiceTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;

  @Test
  @DisplayName("should get students")
  void shouldGetStudents() {
	// given
	studentService.save(createStudent());
	studentService.save(createStudent());
	studentService.save(createStudent());
	studentService.save(createStudent());
	studentService.save(createStudent());

	// when
	Page<Student> students = studentService.getStudents(createStudentSearchCriteria());

	// then
  }

  StudentSearchCriteria createStudentSearchCriteria() {
	return StudentSearchCriteria.builder()
		.page(0)
		.size(4)
		.sortBy("id")
		.firstName("John")
		.lastName("Doe")
		.build();
  }

  Student createStudent() {
	return Student.builder()
		.firstName("John")
		.lastName("Doe")
		.age(24)
		.build();
  }
}