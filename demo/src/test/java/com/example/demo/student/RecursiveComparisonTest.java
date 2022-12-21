package com.example.demo.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RecursiveComparisonTest {

  @Test
  void name() {
	Student student = new Student();
	student.setId(123L);
	student.setName("John");
	student.setAge(22);

  	StudentDTO studentDTO = new StudentDTO();
	studentDTO.setName("John");
	studentDTO.setAge(22);

	assertThat(student).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(studentDTO);
  }
}
