package com.example.demo.commons;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "classpath:clean-data.sql", executionPhase = AFTER_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTest {

  @Container
  public static TestPostgresContainer postgres = TestPostgresContainer
	  .getInstance();

  @Autowired
  protected NamedParameterJdbcOperations jdbc;

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

  protected Response deleteHttpCall(String url) {
	return given()
		.port(port)
		.contentType(JSON)
		.log().all()
		.when()
		.delete(url);
  }

  protected Response putHttpCall(Object body, String url) {
	return given()
		.port(port)
		.body(body)
		.contentType(JSON)
		.log().all()
		.when()
		.put(url);
  }
}
