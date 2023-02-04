package com.example.demo.modules.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import com.example.demo.commons.AbstractIntegrationTest;
import com.example.demo.modules.student.domain.Student;
import com.example.demo.modules.student.statistics.StudentStatisticsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "classpath:data-for-statistics.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "classpath:clean-data.sql", executionPhase = AFTER_TEST_METHOD)
class StudentStatisticsServiceIntegrationTest extends AbstractIntegrationTest {

  private final ArgumentCaptor<Student> bestStudentCaptor = ArgumentCaptor.forClass(Student.class);
  private final ArgumentCaptor<Integer> studentsCountCaptor = ArgumentCaptor.forClass(Integer.class);
  private final ArgumentCaptor<Integer> gradesCountCaptor = ArgumentCaptor.forClass(Integer.class);
  private final ArgumentCaptor<Double> averageCaptor = ArgumentCaptor.forClass(Double.class);

  @Autowired
  private StudentStatisticsService studentStatisticsService;

  @Test
  @DisplayName("should calculate statistics")
  void shouldCalculateStatistics() {
	// given
	Long expectedBestStudentId = 1000003L;
	Integer expectedStudentsCount = 3;
	Integer expectedGradesCount = 12;
	Double expectedAverage = 3.33;

	// when
	studentStatisticsService.calculateStatistics();

	// then
	verify(studentStatisticsReportGenerator, times(1)).generateReport(
		bestStudentCaptor.capture(), studentsCountCaptor.capture(), gradesCountCaptor.capture(), averageCaptor.capture());
	assertThat(bestStudentCaptor.getValue().getId()).isEqualTo(expectedBestStudentId);
	assertThat(studentsCountCaptor.getValue()).isEqualTo(expectedStudentsCount);
	assertThat(gradesCountCaptor.getValue()).isEqualTo(expectedGradesCount);
	assertThat(averageCaptor.getValue()).isEqualTo(expectedAverage);
  }
}
