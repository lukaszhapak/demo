package com.example.demo.job.movie;


import com.example.demo.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

class MovieStatisticsJobTest extends AbstractIntegrationTest {

  @Autowired
  private JdbcOperations jdbc;
  @Autowired
  private MovieStatisticsJob movieStatisticsJob;

  @Test
  @DisplayName("should calculate statistics")
  void shouldCalculateStatistics() {
	// given
	jdbc.execute("insert into movie (id, title, author) values (1000000, 'smierc w wenecji', 'andrzej');");

	// when
	movieStatisticsJob.calculateStatistics();

	// then
  }

}
