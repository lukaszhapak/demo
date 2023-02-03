package com.example.demo.modules.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class StudentStatisticsJobTest {

  private final ArgumentCaptor<Student> bestStudentCaptor = ArgumentCaptor.forClass(Student.class);
  private final ArgumentCaptor<Integer> studentsCountCaptor = ArgumentCaptor.forClass(Integer.class);
  private final ArgumentCaptor<Integer> gradesCountCaptor = ArgumentCaptor.forClass(Integer.class);
  private final ArgumentCaptor<Double> averageCaptor = ArgumentCaptor.forClass(Double.class);

  private final StudentRepositoryInMemory studentRepository = new StudentRepositoryInMemory();
  private final StudentStatisticsReportGenerator studentStatisticsReportGenerator = mock(StudentStatisticsReportGenerator.class);
  private final StudentStatisticsJob studentStatisticsJob = new StudentStatisticsJob(
	  new StudentStatisticsService(studentRepository, new StudentService(studentRepository, new StudentValidator()), studentStatisticsReportGenerator));

  @BeforeEach
  void setUp() {
	studentRepository.cleanData();
	studentRepository.insertStatisticsData();
  }

  @Test
  @DisplayName("should calculate statistics")
  void shouldCalculateStatistics() {
	// given
	Long expectedBestStudentId = 1000003L;
	Integer expectedStudentsCount = 3;
	Integer expectedGradesCount = 12;
	Double expectedAverage = 3.33;

	// when
	studentStatisticsJob.calculateStatistics();

	// then
	verify(studentStatisticsReportGenerator, times(1)).generateReport(bestStudentCaptor.capture(),
		studentsCountCaptor.capture(), gradesCountCaptor.capture(), averageCaptor.capture());
	assertThat(bestStudentCaptor.getValue().getId()).isEqualTo(expectedBestStudentId);
	assertThat(studentsCountCaptor.getValue()).isEqualTo(expectedStudentsCount);
	assertThat(gradesCountCaptor.getValue()).isEqualTo(expectedGradesCount);
	assertThat(averageCaptor.getValue()).isEqualTo(expectedAverage);
  }

  @Test
  @DisplayName("should not fail on calculating statistics with no students")
  void shouldNotFailOnCalculatingStatisticsWithNoStudents() {
	// given
	studentRepository.cleanData();

	// when
	studentStatisticsJob.calculateStatistics();

	// then
  }
}