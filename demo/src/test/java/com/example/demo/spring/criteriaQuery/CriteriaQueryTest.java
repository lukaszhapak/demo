package com.example.demo.spring.criteriaQuery;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class CriteriaQueryTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;

  @Test
  @DisplayName("should get students")
  void shouldGetStudents() {
	// given
	studentService.save(createStudent());
	studentService.save(createStudent());
	studentService.save(createStudent());
	Student jimmy = createStudent();
	jimmy.setFirstName("Jimmy");
	studentService.save(jimmy);

	// when
	Page<Student> students = studentService.getStudents(createStudentSearchCriteria());

	// then
	assertThat(students.getTotalElements()).isEqualTo(1);
  }

  StudentSearchCriteria createStudentSearchCriteria() {
	return StudentSearchCriteria.builder()
		.page(0)
		.size(4)
		.sortBy("id")
		.firstName("Jimmy")
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