package com.example.hexagonal.job.movie;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovieGenerateJobTest {

  private final MovieGenerateService movieGenerateService = mock(MovieGenerateService.class);
  private final MovieGenerateJob movieGenerateJob = new MovieGenerateJob(
	  movieGenerateService);

  @Test
  @DisplayName("should invoke movie statistics service")
  void shouldInvokeMovieStatisticsService() {
	// when
	movieGenerateJob.generateMovie();

	// then
	verify(movieGenerateService, times(1)).generateMovie();
  }
}