package com.example.demo.spring.criteriaQuery;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class CriteriaQueryTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;

  @BeforeEach
  void setUp() {
	studentService.save(createStudent());
	studentService.save(createStudent());
	studentService.save(createStudent());
  }

  @Test
  @DisplayName("should get student by first name")
  void shouldGetStudentByFirstName() {
	// given
	Student student = createStudent();
	student.setFirstName("Jimmy");
	studentService.save(student);
	StudentSearchCriteria studentSearchCriteria = createStudentSearchCriteria();
	studentSearchCriteria.setFirstName(student.getFirstName());

	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria);

	// then
	assertThat(students.getTotalElements()).isEqualTo(1);
  }

  @Test
  @DisplayName("should get student by street name")
  void shouldGetStudentByStreetName() {
	// given
	Student student = createStudent();
	student.getAddress().setStreetName("Ghj");
	studentService.save(student);
	StudentSearchCriteria studentSearchCriteria = createStudentSearchCriteria();
	studentSearchCriteria.setStreetName(student.getAddress().getStreetName());

	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria);

	// then
	assertThat(students.getTotalElements()).isEqualTo(1);
  }

  @Test
  @DisplayName("should get student by older than")
  void shouldGetStudentByOlderThan() {
	// given
	Student student = createStudent();
	student.setAge(32);
	studentService.save(student);
	StudentSearchCriteria studentSearchCriteria = createStudentSearchCriteria();
	studentSearchCriteria.setOlderThan(30);

	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria);

	// then
	assertThat(students.getTotalElements()).isEqualTo(1);
  }

  StudentSearchCriteria createStudentSearchCriteria() {
	return StudentSearchCriteria.builder()
		.page(0)
		.size(10)
		.sortBy("id")
		.build();
  }

  Student createStudent() {
	return Student.builder()
		.firstName("John")
		.lastName("Doe")
		.age(24)
		.address(Address.builder()
			.streetName("Asd")
			.build())
		.build();
  }
}