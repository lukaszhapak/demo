package com.example.demo.modules.student;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

class StudentServiceIntegrationTest extends AbstractIntegrationTest {

  @BeforeEach
  void setUp() {
	jdbc.update(
		"INSERT INTO STUDENT(ID, NAME, AGE, GRADES) VALUES "
			+ "(1000001, 'John', 22, '2,5,4,3,3'),"
			+ "(1000002, 'John', 22, '2,5,4,3,3'),"
			+ "(1000003, 'John', 22, '2,5,4,3,3');",
		new MapSqlParameterSource());
  }

  @AfterEach
  void tearDown() {
	// clean up
	jdbc.update("DELETE FROM STUDENT", new MapSqlParameterSource());
  }

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
