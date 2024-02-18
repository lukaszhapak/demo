package com.example.demo.nonspring.test.assertJ;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecursiveComparisonTest {


  static Student getStudent() {
	return Student.builder()
		.id(14L)
		.name("John")
		.age(22)
		.build();
  }

  static StudentDTO getStudentDTO() {
	return StudentDTO.builder()
		.name("John")
		.age(22)
		.build();
  }

  @Test
  @DisplayName("recursive comparison")
  void recursiveComparison() {
	// Setup:
	Student student = getStudent();
	StudentDTO studentDTO = getStudentDTO();

	// When:

	// Then:
	assertThat(student).isNotEqualTo(studentDTO); // not equal, different class
	assertThat(studentDTO).usingRecursiveComparison().isEqualTo(student); // is equal to because DTO have no id
	assertThat(student).usingRecursiveComparison().isNotEqualTo(studentDTO); // not equal to because student have id
	assertThat(student).usingRecursiveComparison().ignoringFields("id").isEqualTo(studentDTO);
	assertThat(student).usingRecursiveComparison().ignoringActualNullFields().isNotEqualTo(studentDTO);
	assertThat(student).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(studentDTO);

	student.setName("Jim");
	assertThat(studentDTO).usingRecursiveComparison().isNotEqualTo(student);
	assertThat(student).usingRecursiveComparison().ignoringFields("id").isNotEqualTo(studentDTO);
  }
}