package com.example.hexagonal.job.movie;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovieStatisticsJobTest {

  private final MovieStatisticsService movieStatisticsService = mock(MovieStatisticsService.class);
  private final MovieStatisticsJob movieStatisticsJob = new MovieStatisticsJob(
	  movieStatisticsService);

  @Test
  @DisplayName("should invoke movie statistics service")
  void shouldInvokeMovieStatisticsService() {
	// given

	// when
	movieStatisticsJob.calculateStatistics();

	// then
	verify(movieStatisticsService, times(1)).count();
  }
}