package com.example.hexagonal;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.hexagonal.domain.movie.Movie;
import com.example.hexagonal.listener.movie.MovieCreatedListener;
import io.restassured.response.Response;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AbstractIntegrationTest {

  @MockBean
  protected MovieCreatedListener movieCreatedListener;

  @Autowired
  protected JdbcOperations jdbc;

  @LocalServerPort
  protected int port;

  protected Response getHttpCall(String url) {
	return given()
		.port(port)
		.log().all()
		.when()
		.get(url);
  }

  protected Response postHttpCall(Object body, String url) {
	return given()
		.port(port)
		.body(body)
		.contentType(JSON)
		.log().all()
		.when()
		.post(url);
  }

  protected List<Movie> fetchMovieEntities() {
	return jdbc.query("SELECT * FROM MOVIE",
		new BeanPropertyRowMapper<>(Movie.class));
  }
}
