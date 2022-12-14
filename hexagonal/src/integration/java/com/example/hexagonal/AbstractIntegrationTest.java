package com.example.hexagonal;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class AbstractIntegrationTest {

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

}
