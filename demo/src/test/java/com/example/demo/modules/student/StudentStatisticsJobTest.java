package com.example.demo.modules.student;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentStatisticsJobTest {

  private final StudentService studentService = mock(StudentService.class);
  private final StudentStatisticsJob studentStatisticsJob = new StudentStatisticsJob(
	  studentService);

  @Test
  @DisplayName("should call student service count method")
  void shouldCallStudentServiceCountMethod() {
	// when
	studentStatisticsJob.calculateStatistics();

	// then
	verify(studentService, times(1)).count();
  }
}