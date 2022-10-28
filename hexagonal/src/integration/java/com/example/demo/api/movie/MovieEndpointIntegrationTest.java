package com.example.demo.api.movie;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.api.AbstractIntegrationTest;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

class MovieEndpointIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private JdbcOperations jdbc;

  @Test
  @DisplayName("should get movie by id")
  void shouldGetSMovieById() {
	// given
	jdbc.execute(
		"insert into movie (id, title, author) values (100, 'smierc w wenecji', 'andrzej');");
	long id = 100L;

	// when
	MovieResponse movieResponse = given()
		.log().all()
		.port(port)
		.expect()
		.statusCode(200)
		.when()
		.get("/api/movie/" + id)
		.getBody().as(MovieResponse.class);

	//then
	assertThat(movieResponsesAreEqual(movieResponse, getMovieResponse())).isTrue();
  }

  @Test
  @DisplayName("should get 404 when movie not found")
  void shouldGet404WhenMovieNotFound() {
	// given
	long id = 100L;

	// when
	given()
		.log().all()
		.port(port)
		.expect()
		.statusCode(404)
		.when()
		.get("/api/movie/" + id);
  }

  boolean movieResponsesAreEqual(MovieResponse movieResponse1, MovieResponse movieResponse2) {
	if (!Objects.equals(movieResponse1.getId(), movieResponse2.getId())) {
	  return false;
	}
	if (!Objects.equals(movieResponse1.getTitle(), movieResponse2.getTitle())) {
	  return false;
	}
	return Objects.equals(movieResponse1.getAuthor(), movieResponse2.getAuthor());
  }

  MovieResponse getMovieResponse() {
	MovieResponse movieResponse = new MovieResponse();
	movieResponse.setId(100L);
	movieResponse.setTitle("smierc w wenecji");
	movieResponse.setAuthor("andrzej");
	return movieResponse;
  }
}
