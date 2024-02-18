package com.example.demo.spring.data.criteriaQuery;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.commons.AbstractRestAssuredIntegrationTest;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class CriteriaQueryTest extends AbstractRestAssuredIntegrationTest {

  @Autowired
  StudentService studentService;

  @BeforeEach
  void setUp() {
	studentService.save(createStudent());
	studentService.save(createStudent());
	studentService.save(createStudent());
  }

  @AfterEach
  void tearDown() {
	studentService.deleteAll();
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

  @Test
  @DisplayName("should get student by minimal age")
  void shouldGetStudentByMinimalAge() {
	// given
	Student student = createStudent();
	student.setAge(32);
	studentService.save(student);
	StudentSearchCriteria studentSearchCriteria = createStudentSearchCriteria();
	studentSearchCriteria.setMinimalAge(32);

	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria);

	// then
	assertThat(students.getTotalElements()).isEqualTo(1);
  }

  @Test
  @DisplayName("should get student by last names")
  void shouldGetStudentByLastNames() {
	// given
	Student student = createStudent();
	student.setLastName("QWE");
	studentService.save(student);
	Student student2 = createStudent();
	student2.setLastName("RTY");
	studentService.save(student2);
	StudentSearchCriteria studentSearchCriteria = createStudentSearchCriteria();
	studentSearchCriteria.setLastNames(List.of("QWE", "RTY"));

	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria);

	// then
	assertThat(students.getTotalElements()).isEqualTo(2);
  }

  @Test
  @DisplayName("should get all students when last names is empty list")
  void shouldGetAllStudentsWhenLastNamesIsEmptyList() {
	// given
	Student student = createStudent();
	student.setLastName("QWE");
	studentService.save(student);
	Student student2 = createStudent();
	student2.setLastName("RTY");
	studentService.save(student2);
	StudentSearchCriteria studentSearchCriteria = createStudentSearchCriteria();
	studentSearchCriteria.setLastNames(Collections.emptyList());

	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria);

	// then
	assertThat(students.getTotalElements()).isEqualTo(5);
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