package com.example.demo.modules.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "classpath:data-for-statistics.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "classpath:clean-data.sql", executionPhase = AFTER_TEST_METHOD)
class StudentServiceIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private StudentService studentService;

  @Test
  @DisplayName("should calculate statistics")
  void shouldCalculateStatistics() {
	// given
	Long expectedStudentCount = 2L;

	// when
	Long actualStudentCount = studentService.count();

	// then
	assertThat(actualStudentCount).isEqualTo(expectedStudentCount);
  }

}
