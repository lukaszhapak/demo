package com.example.demo.nonspring.test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LombokChainedSetterTest {

  @Test
  @DisplayName("should get student")
  void shouldGetStudent() {
	// given
	Student student = getStudent().setName("Invalid name");

	// when

	// then
	assertThat(student.getName()).isEqualTo("Invalid name");
  }

  static Student getStudent() {
	return new Student("John", 24);
  }
}
