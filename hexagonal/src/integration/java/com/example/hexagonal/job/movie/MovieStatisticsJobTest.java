package com.example.hexagonal.job.movie;


import com.example.hexagonal.AbstractIntegrationTest;
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
	jdbc.execute(
		"insert into movie (id, title, author, category) values (1000000, 'smierc w wenecji', 'andrzej', 'COMEDY');");

	// when
	movieStatisticsJob.calculateStatistics();

	// then
	// TODO

	// clean up
	jdbc.execute("delete from movie");
  }

}
