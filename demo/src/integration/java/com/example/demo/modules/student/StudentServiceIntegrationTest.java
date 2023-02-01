package com.example.demo.modules.student;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StudentServiceIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private StudentService studentService;

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
