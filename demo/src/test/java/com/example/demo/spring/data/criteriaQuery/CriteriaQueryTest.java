package com.example.demo.spring.data.criteriaQuery;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class CriteriaQueryTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;

  Student john = Student.builder()
	  .firstName("John")
	  .lastName("Doe")
	  .age(24)
	  .address(Address.builder()
		  .streetName("Oak street")
		  .flatNumber("51")
		  .build())
	  .build();

  Student jim = Student.builder()
	  .firstName("Jim")
	  .lastName("Newman")
	  .age(21)
	  .address(Address.builder()
		  .streetName("School street")
		  .flatNumber("12")
		  .build())
	  .build();

  Student michael = Student.builder()
	  .firstName("Michael")
	  .lastName("Smith")
	  .age(27)
	  .address(Address.builder()
		  .streetName("Student street")
		  .flatNumber("123")
		  .build())
	  .build();

  StudentSearchCriteria studentSearchCriteria = StudentSearchCriteria.builder()
	  .page(0)
	  .size(10)
	  .sortBy("id")
	  .build();

  @BeforeEach
  void setUp() {
	saveStudents(john, jim, michael);
  }

  @AfterEach
  void tearDown() {
	studentService.deleteAll();
  }

  @Test
  @DisplayName("should get student by first name")
  void shouldGetStudentByFirstName() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setFirstName("Jim"));

	// then
	assertThat(students.getTotalElements()).isEqualTo(1);
  }

  @Test
  @DisplayName("should get student by street name")
  void shouldGetStudentByStreetName() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setStreetName("Oak street"));

	// then
	assertThat(students.getTotalElements()).isEqualTo(1);
  }

  @Test
  @DisplayName("should get student by older than")
  void shouldGetStudentByOlderThan() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setOlderThan(21));

	// then
	assertThat(students.getTotalElements()).isEqualTo(2);
  }

  @Test
  @DisplayName("should get student by minimal age")
  void shouldGetStudentByMinimalAge() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setMinimalAge(24));

	// then
	assertThat(students.getTotalElements()).isEqualTo(2);
  }

  @Test
  @DisplayName("should get student by last names")
  void shouldGetStudentByLastNames() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setLastNames(List.of("Doe", "Newman")));

	// then
	assertThat(students.getTotalElements()).isEqualTo(2);
  }

  @Test
  @DisplayName("should get all students when last names is empty list")
  void shouldGetAllStudentsWhenLastNamesIsEmptyList() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setLastNames(Collections.emptyList()));

	// then
	assertThat(students.getTotalElements()).isEqualTo(3);
  }

  void saveStudents(Student... students) {
	for (Student student : students) {
	  studentService.save(student);
	}
  }
}