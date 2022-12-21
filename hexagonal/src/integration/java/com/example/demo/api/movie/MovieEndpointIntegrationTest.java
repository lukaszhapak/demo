package com.example.demo.api.movie;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.adapters.movie.hibernate.HibernateMovie;
import com.example.demo.api.AbstractIntegrationTest;
import com.example.demo.domain.movie.Movie;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

	// then
	assertThat(movieResponse).usingRecursiveComparison().isEqualTo(getMovieResponse());
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

  @Test
  @DisplayName("should create movie")
  void shouldCreateMovie() {
	// given
	MovieRequest movieRequest = getMovieRequest();

	// when
	MovieResponse movieResponse = given()
		.port(port)
		.body(movieRequest)
		.contentType(JSON)
		.log().all()
		.when()
		.log().all()
		.post("/api/movie")
		.getBody().as(MovieResponse.class);

	// then
	Movie movieEntity = jdbc.queryForObject("select * from movie where id = 1",
		new BeanPropertyRowMapper<>(Movie.class));

	assertThat(movieRequest).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(movieResponse);
	assertThat(movieResponse.getId()).isNotNull();

	assertThat(movieRequest).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(movieEntity);
	assertThat(movieEntity.getId()).isNotNull();

	jdbc.execute("delete from movie");
  }

  MovieResponse getMovieResponse() {
	MovieResponse movieResponse = new MovieResponse();
	movieResponse.setId(100L);
	movieResponse.setTitle("smierc w wenecji");
	movieResponse.setAuthor("andrzej");
	return movieResponse;
  }

  MovieRequest getMovieRequest() {
	MovieRequest movieRequest = new MovieRequest();
	movieRequest.setTitle("smierc w wenecji");
	movieRequest.setAuthor("andrzej");
	return movieRequest;
  }
}
