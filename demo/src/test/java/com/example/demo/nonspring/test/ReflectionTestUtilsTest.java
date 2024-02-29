package com.example.demo.nonspring.test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class ReflectionTestUtilsTest {

  @Test
  @DisplayName("should get student")
  void shouldGetStudent() {
	// given
	Student student = getStudent(Map.of("name", "Invalid name"));

	// when

	// then
	assertThat(student.getName()).isEqualTo("Invalid name");
  }

  static Student getStudent(Map<String, Object> fieldsValues) {
	Student validPatientDtoIn = getStudent();
	fieldsValues.forEach((key, value) -> ReflectionTestUtils.setField(validPatientDtoIn, key, value));
	return validPatientDtoIn;
  }

  static Student getStudent() {
	return new Student("John", 24);
  }
}
