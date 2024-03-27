package com.example.demo.spring.data.specification;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

class SpecificationTest extends AbstractIntegrationTest {

  @Autowired
  StudentService studentService;

  Student john = Student.builder()
	  .firstName("John")
	  .lastName("Doe")
	  .age(24)
	  .date(LocalDateTime.of(2024, 2, 25, 0, 0, 0))
	  .address(Address.builder()
		  .streetName("Oak street")
		  .flatNumber("51")
		  .build())
	  .build();

  Student jim = Student.builder()
	  .firstName("Jim")
	  .lastName("Newman")
	  .age(21)
	  .date(LocalDateTime.of(2024, 2, 10, 0, 0, 0))
	  .address(Address.builder()
		  .streetName("School street")
		  .flatNumber("12")
		  .build())
	  .build();

  Student michael = Student.builder()
	  .firstName("Michael")
	  .lastName("Smith")
	  .age(27)
	  .date(LocalDateTime.of(2024, 3, 1, 0, 0, 0))
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
	assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(jim));
  }

  @Test
  @DisplayName("should get student by street name")
  void shouldGetStudentByStreetName() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setStreetName("Oak street"));

	// then
	assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john));
  }

  @Test
  @DisplayName("should get student by older than")
  void shouldGetStudentByOlderThan() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setOlderThan(21));

	// then
	assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john, michael));
  }

  @Test
  @DisplayName("should get student by minimal age")
  void shouldGetStudentByMinimalAge() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setMinimalAge(24));

	// then
	assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john, michael));
  }

  @Test
  @DisplayName("should get student by last names")
  void shouldGetStudentByLastNames() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setLastNames(List.of("Doe", "Newman")));

	// then
	assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john, jim));
  }

  @Test
  @DisplayName("should get all students when last names is empty list")
  void shouldGetAllStudentsWhenLastNamesIsEmptyList() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setLastNames(Collections.emptyList()));

	// then
	assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john, jim, michael));
  }

  @Test
  @DisplayName("should get student by date before")
  void shouldGetStudentDateBefore() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setDateBefore(LocalDateTime.of(2024, 2, 25, 0, 0, 0)));

	// then
	assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(jim));
  }

  @Test
  @DisplayName("should get student by date after")
  void shouldGetStudentDateAfter() {
	// when
	Page<Student> students = studentService.getStudents(studentSearchCriteria.setDateAfter(LocalDateTime.of(2024, 2, 25, 0, 0, 0)));

	// then
	assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(michael));
  }

  void saveStudents(Student... students) {
	for (Student student : students) {
	  studentService.save(student);
	}
  }
}