package com.example.hexagonal.api.movie;

import static com.example.hexagonal.domain.movie.MovieCategory.COMEDY;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.hexagonal.AbstractIntegrationTest;
import com.example.hexagonal.domain.movie.Movie;
import io.restassured.response.Response;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovieEndpointIntegrationTest extends AbstractIntegrationTest {

  public static final String URL = "/api/movie/";

  @Test
  @DisplayName("should get movie by id")
  void shouldGetSMovieById() {
	// given
	jdbc.execute(
		"INSERT INTO MOVIE (ID, TITLE, AUTHOR, CATEGORY) VALUES (1000000, 'smierc w wenecji', 'andrzej', 'COMEDY');");
	long id = 1000000L;

	// when
	Response response = getHttpCall(URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	MovieResponse movieResponse = response.getBody().as(MovieResponse.class);
	assertThat(movieResponse.getId()).isEqualTo(id);
	assertThat(movieResponse).usingRecursiveComparison().ignoringExpectedNullFields()
		.isEqualTo(getMovieRequest());

	// clean up
	jdbc.execute("DELETE FROM MOVIE");
  }

  @Test
  @DisplayName("should get 404 when movie not found")
  void shouldGet404WhenMovieNotFound() {
	// given
	long id = 1000000L;

	// when
	Response response = getHttpCall(URL + id);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  @DisplayName("should create movie")
  void shouldCreateMovie() {
	// given
	MovieRequest movieRequest = getMovieRequest();

	// when
	Response response = postHttpCall(movieRequest, URL);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

	MovieResponse movieResponse = response.getBody().as(MovieResponse.class);
	assertThat(movieRequest).usingRecursiveComparison().isEqualTo(movieResponse);
	assertThat(movieResponse.getId()).isNotNull();

	List<Movie> movieEntities = fetchMovieEntities();
	assertThat(movieEntities.size()).isEqualTo(1);
	assertThat(movieRequest).usingRecursiveComparison().ignoringActualNullFields()
		.isEqualTo(movieEntities.get(0));
	assertThat(movieEntities.get(0).getId()).isNotNull();

	// clean up
	jdbc.execute("DELETE FROM MOVIE");
  }

  @Test
  @DisplayName("should not create movie with invalid fields")
  void shouldNotCreateMovieWithInvalidFields() {
	// given
	MovieRequest movieRequest = getMovieRequest();
	movieRequest.setTitle("t");
	movieRequest.setAuthor("a");

	// when
	Response response = postHttpCall(movieRequest, URL);

	// then
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
  }

  private MovieRequest getMovieRequest() {
	MovieRequest movieRequest = new MovieRequest();
	movieRequest.setTitle("smierc w wenecji");
	movieRequest.setAuthor("andrzej");
	movieRequest.setCategory(COMEDY);
	return movieRequest;
  }
}
