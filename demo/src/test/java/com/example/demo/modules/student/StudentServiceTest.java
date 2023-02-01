package com.example.demo.modules.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

  private final StudentRepository studentRepository = new StudentRepositoryInMemory();
  private final StudentValidator studentValidator = new StudentValidator();
  private final StudentService studentService = new StudentService(studentRepository,
	  studentValidator);

  @Test
  @DisplayName("should calculate statistics")
  void shouldCalculateStatistics() {
	// given
	Long expectedStudentCount = 3L;

	// when
	Long actualStudentCount = studentService.count();

	// then
	assertThat(actualStudentCount).isEqualTo(expectedStudentCount);
  }
}