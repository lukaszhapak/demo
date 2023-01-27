package com.example.hexagonal.job.movie;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.hexagonal.AbstractIntegrationTest;
import com.example.hexagonal.domain.movie.Movie;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MovieGenerateServiceTest extends AbstractIntegrationTest {

  @Autowired
  private MovieGenerateService movieGenerateService;

  @Test
  @DisplayName("should generate movie")
  void shouldGenerateMovie() {
	// when
    movieGenerateService.generateMovie();

	// then
    List<Movie> movieEntities = fetchMovieEntities();
    assertThat(movieEntities.size()).isEqualTo(1);
    assertThat(movieEntities.get(0).getId()).isNotNull();

    // clean up
	jdbc.execute("DELETE FROM MOVIE");
  }
}
