package com.example.commons.test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

  @Container
  public static TestPostgresContainer postgres = TestPostgresContainer.getContainer();

  @LocalServerPort
  protected int port;

  @Autowired
  protected NamedParameterJdbcOperations jdbc;

  protected Response getHttpCall(String url) {
	return RestAssured.given()
		.port(port)
		.log().all()
		.when()
		.get(url);
  }

  protected Response postHttpCall(Object body, String url) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post(url);
  }

  protected Response deleteHttpCall(String url) {
	return RestAssured.given()
		.port(port)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.delete(url);
  }

  protected Response putHttpCall(Object body, String url) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.put(url);
  }
}
